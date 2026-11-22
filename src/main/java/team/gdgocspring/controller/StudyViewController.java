package team.gdgocspring.controller;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team.gdgocspring.dto.StudyListViewResponse;
import team.gdgocspring.dto.StudyResponse;
import team.gdgocspring.service.StudyService;

import java.util.List;

@Controller
public class StudyViewController {
    private final ObjectProvider<ClientRegistrationRepository> registrations;
    private final StudyService studyService;

    public StudyViewController(ObjectProvider<ClientRegistrationRepository> registrations, StudyService studyService) {
        this.registrations = registrations;
        this.studyService = studyService;;
    }

    @GetMapping({"/", "/studies"})
    public String getStudies(Model model) {
        List<StudyListViewResponse> studies = studyService.findAll().stream()
                .map(StudyListViewResponse::new)
                .toList();
        model.addAttribute("studies", studies);

        return "studyList";
    }

    @GetMapping("/studies/1")
    public String detail() {
        return "studyDetail";
    }

    @GetMapping("/studies/new")
    public String create(Model model) {
        model.addAttribute("editing", false);
        return "studyForm";
    }

    @GetMapping("/studies/1/edit")
    public String edit(Model model) {
        model.addAttribute("editing", true);
        return "studyForm";
    }

    @GetMapping("/studies/1/applicants")
    public String applicants() {
        return "applicants";
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "myPage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("googleEnabled", registrations.getIfAvailable() != null);
        return "login";
    }
}
