package team.gdgocspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gdgocspring.domain.Study;
import team.gdgocspring.domain.User;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.repository.StudyRepository;
import team.gdgocspring.repository.UserRepository;

import java.util.List;

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

    // DB에 저장되어 있는 스터디 모집글을 모두 가져오는 메서드
    // study 테이블에 저장된 모든 테이블 조회
    public List<Study> findAll() {return studyRepository.findAll();}

    // DB에 저장되어 있는 스터디 모집글의 ID를 이용하여 글을 조회하는 메서드
    public Study findById(long id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디입니다. : " + id));
    }

}
