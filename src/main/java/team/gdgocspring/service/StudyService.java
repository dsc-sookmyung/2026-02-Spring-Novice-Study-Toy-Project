package team.gdgocspring.service;

import team.gdgocspring.domain.Study;
import team.gdgocspring.domain.User;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.repository.StudyRepository;
import team.gdgocspring.repository.UserRepository;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    public StudyService(StudyRepository studyRepository,  UserRepository userRepository) {
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Study save(AddStudyRequest request, OidcUser account) {

        String leaderID = account.getSubject();

        User leader = userRepository.findById(leaderID).orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Study study = request.toEntity(leader);

        return studyRepository.save(study);
    }

    public List<Study> findAll() {
        return studyRepository.findAll();
    }
}