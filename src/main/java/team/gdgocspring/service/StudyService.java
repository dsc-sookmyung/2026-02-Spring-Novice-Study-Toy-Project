package team.gdgocspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gdgocspring.domain.Study;
import team.gdgocspring.domain.User;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.repository.StudyRepository;
import team.gdgocspring.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Study save(AddStudyRequest request, String googleSubject) {
        User user = userRepository.findById(googleSubject)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new IllegalArgumentException("제목을 입력해 주세요.");
        }
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new IllegalArgumentException("스터디 소개를 입력해 주세요.");
        }
        if (request.getCapacity() == null || request.getCapacity() < 2) {
            throw new IllegalArgumentException("모집 정원은 스터디장을 포함해 2명 이상이어야 합니다.");
        }

        return studyRepository.save(request.toEntity(user.getGoogleSubject()));
    }
}
