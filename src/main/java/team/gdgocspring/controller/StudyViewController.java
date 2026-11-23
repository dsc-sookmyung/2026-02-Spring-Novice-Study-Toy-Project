
package team.gdgocspring.controller;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.service.StudyService;

@Controller
public class StudyViewController {

    private final ObjectProvider<ClientRegistrationRepository> registrations;
    private final StudyService studyService;

    public StudyViewController(
            ObjectProvider<ClientRegistrationRepository> registrations,
            StudyService studyService) {

        this.registrations = registrations;
        this.studyService = studyService;
    }

    // 스터디 등록 처리
    @PostMapping("/studies")
    public String saveStudy(
            @ModelAttribute AddStudyRequest request,
            @AuthenticationPrincipal OidcUser account) {

        studyService.save(request, account);

        return "redirect:/studies";
    }

    @GetMapping({"/", "/studies"})
    public String list(Model model) {
        model.addAttribute("studies", studyService.findAll());
        return "studyList";
    }

    // 스터디 상세 화면
    @GetMapping("/studies/1")
    public String detail() {
        return "studyDetail";
    }

    // 스터디 등록 화면
    @GetMapping("/studies/new")
    public String create(Model model) {
        model.addAttribute("editing", false);
        return "studyForm";
    }

    // 스터디 신청자 화면
    @GetMapping("/studies/1/applicants")
    public String applicants() {
        return "applicants";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage() {
        return "myPage";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(
                "googleEnabled",
                registrations.getIfAvailable() != null
        );
        return "login";
    }
}
