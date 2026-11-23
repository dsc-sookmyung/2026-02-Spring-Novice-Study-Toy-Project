package team.gdgocspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.gdgocspring.domain.Study;
import team.gdgocspring.repository.StudyRepository;

// 스터디 수정 기능의 비즈니스 로직을 처리하는 서비스 클래스
@Service
public class StudyUpdateService {

    private final StudyRepository studyRepository;

    // 생성자를 통해 Repository를 주입받음 (의존성 주입, DI)
    public StudyUpdateService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    // 스터디 수정 메서드
    // @Transactional: 트랜잭션 안에서 엔티티 변경사항을 DB에 반영
    @Transactional
    public void updateStudy(Long studyId, String googleSubject,
                            String title, String content,
                            Integer capacity, String recruitmentStatus) {

        // 1. 수정할 스터디를 ID로 조회
        // 해당 ID의 스터디가 없으면 예외 발생
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 스터디입니다."));

        // 2. 수정 권한 확인
        // 스터디장과 로그인한 사용자의 Google 식별자를 비교
        if (!study.getLeader().getGoogleSubject().equals(googleSubject)) {
            throw new IllegalArgumentException(
                    "스터디장만 수정할 수 있습니다.");
        }

        // 3. 입력값 검증
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "스터디 제목을 입력해야 합니다.");
        }

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException(
                    "스터디 내용을 입력해야 합니다.");
        }

        if (capacity == null || capacity < 1) {
            throw new IllegalArgumentException(
                    "모집 정원은 1명 이상이어야 합니다.");
        }

        // 4. 스터디 정보 수정
        // JPA 변경 감지(Dirty Checking)로 변경사항을 DB에 반영
        study.update(title, content, capacity, recruitmentStatus);
    }
}
