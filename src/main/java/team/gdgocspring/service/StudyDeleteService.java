package team.gdgocspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gdgocspring.domain.Study;
import team.gdgocspring.repository.StudyRepository;

@Service
public class StudyDeleteService {

    private final StudyRepository studyRepository;

    public StudyDeleteService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Transactional
    public void deleteStudy(Long studyId, String googleSubject) {

        // 1. 삭제할 스터디 조회
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디입니다."));

        // 2. 스터디장인지 확인
        if (!study.getLeader().getGoogleSubject().equals(googleSubject)) {
            throw new IllegalArgumentException("스터디장만 삭제할 수 있습니다.");
        }

        // 3. 스터디 삭제
        studyRepository.delete(study);
    }
}
