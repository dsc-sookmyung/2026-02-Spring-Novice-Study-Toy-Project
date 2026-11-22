package team.gdgocspring.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import team.gdgocspring.domain.Study;
import team.gdgocspring.domain.User;
import team.gdgocspring.repository.StudyRepository;

import java.util.List;

@Service
public class StudyService {
    private final StudyRepository studies;
    public StudyService(StudyRepository studies) {
        this.studies = studies;
    }

    // 등록 메서드
    @Transactional
    public Study createStudy(String title, String description, int capacity, User owner) {
        // 정원 검사
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        // Study 객체 생성
        Study study = new Study(title, description, capacity, owner);
        // Repository를 이용해 저장
        // 저장된 Study 반환
        return studies.save(study);
    }

    // 전체 조회 메서드
    @Transactional(readOnly = true)
    public List<Study> getAllStudies() {
        // Repository에서 모든 Study 조회 후 반환
        return studies.findAll();
    }

    // 상세 조회 메서드
    @Transactional(readOnly = true)
    public Study getStudy(Integer studyId) {
        // studyId로 조회
        // 해당 스터디가 없을 경우 예외 처리
        return studies.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디입니다."));
    }
}
