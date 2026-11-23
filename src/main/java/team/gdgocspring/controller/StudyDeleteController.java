package team.gdgocspring.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.gdgocspring.service.StudyDeleteService;

@Controller
@RequestMapping("/studies")
public class StudyDeleteController {

    private final StudyDeleteService studyDeleteService;

    public StudyDeleteController(StudyDeleteService studyDeleteService) {
        this.studyDeleteService = studyDeleteService;
    }

    // 스터디 삭제 요청 처리
    @PostMapping("/{id}/delete")
    public String deleteStudy(
            @PathVariable Long id,
            @AuthenticationPrincipal OAuth2User principal) {

        // 로그인 여부 확인
        if (principal == null) {
            return "redirect:/login";
        }

        // 로그인한 사용자의 Google ID로 삭제 권한 확인 및 삭제
        String googleSubject = principal.getAttribute("sub");
        studyDeleteService.deleteStudy(id, googleSubject);

        // 삭제 후 스터디 목록으로 이동
        return "redirect:/studies";
    }
}
