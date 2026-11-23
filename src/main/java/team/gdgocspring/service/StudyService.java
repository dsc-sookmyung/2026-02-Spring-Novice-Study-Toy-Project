package team.gdgocspring.service;

import team.gdgocspring.domain.Study;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.repository.StudyRepository;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Transactional
    public Study save(AddStudyRequest request, OidcUser account) {

        String leaderId = account.getSubject();

        Study study = request.toEntity(leaderId);

        return studyRepository.save(study);
    }

    public List<Study> findAll() {
        return studyRepository.findAll();
    }
}