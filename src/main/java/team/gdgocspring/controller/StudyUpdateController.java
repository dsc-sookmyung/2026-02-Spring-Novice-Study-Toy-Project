package team.gdgocspring.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import team.gdgocspring.domain.Study;
import team.gdgocspring.repository.StudyRepository;
import team.gdgocspring.service.StudyUpdateService;

@Controller
@RequestMapping("/studies")
public class StudyUpdateController {

    private final StudyUpdateService studyService;
    private final StudyRepository studyRepository;

    // 생성자 주입으로 Service와 Repository 사용
    public StudyUpdateController(StudyUpdateService studyService,
                                 StudyRepository studyRepository) {
        this.studyService = studyService;
        this.studyRepository = studyRepository;
    }

    // 스터디 수정 화면 표시
    // GET /studies/1/edit
    @GetMapping("/{id}/edit")
    public String editStudy(@PathVariable Long id,
                            Model model,
                            @AuthenticationPrincipal OAuth2User principal) {

        // 로그인 여부 확인
        if (principal == null) {
            return "redirect:/login";
        }

        // 수정할 스터디 조회
        Study study = studyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 스터디입니다."));

        // 스터디장만 수정 화면에 접근 가능
        String googleSubject = principal.getAttribute("sub");

        if (!study.getLeader().getGoogleSubject().equals(googleSubject)) {
            throw new IllegalArgumentException(
                    "스터디장만 수정할 수 있습니다.");
        }

        // HTML에 기존 스터디 정보 전달
        model.addAttribute("study", study);
        model.addAttribute("editing", true);

        return "studyForm";
    }

    // 스터디 수정 요청 처리
    // POST /studies/1/edit
    @PostMapping("/{id}/edit")
    public String updateStudy(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Integer capacity,
            @RequestParam String recruitmentStatus,
            @AuthenticationPrincipal OAuth2User principal) {

        // 로그인 여부 확인
        if (principal == null) {
            return "redirect:/login";
        }

        String googleSubject = principal.getAttribute("sub");

        // 실제 수정 작업은 Service에서 처리
        studyService.updateStudy(
                id, googleSubject, title, content,
                capacity, recruitmentStatus
        );

        // 수정 완료 후 상세 페이지로 이동
        return "redirect:/studies/" + id;
    }
}
