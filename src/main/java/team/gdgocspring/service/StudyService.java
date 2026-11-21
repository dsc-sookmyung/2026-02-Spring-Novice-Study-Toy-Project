package team.gdgocspring.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import team.gdgocspring.domain.Study;
import team.gdgocspring.repository.StudyRepository;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Transactional
    public void delete(long id, String googleSubject) {
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "스터디를 찾을 수 없습니다."));

        if (!study.getLeaderId().equals(googleSubject)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "스터디 개설자만 삭제할 수 있습니다.");
        }

        studyRepository.delete(study);
    }
}