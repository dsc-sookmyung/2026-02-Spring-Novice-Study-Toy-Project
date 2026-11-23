package team.gdgocspring.controller;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import team.gdgocspring.domain.Study;
import team.gdgocspring.service.StudyService;

import java.security.Principal;

@Controller
public class StudyViewController {

    private final ObjectProvider<ClientRegistrationRepository> registrations;
    private final StudyService studyService;

    public StudyViewController(
            ObjectProvider<ClientRegistrationRepository> registrations,
            StudyService studyService
    ) {
        this.registrations = registrations;
        this.studyService = studyService;
    }

    @GetMapping({"/", "/studies"})
    public String list() {
        return "studyList";
    }

    @GetMapping("/studies/{id}")
    public String detail(@PathVariable("id") long id, Principal principal, Model model) {
        Study study = studyService.findById(id);

        model.addAttribute("study", study);
        model.addAttribute("owner", study.getLeaderId().equals(principal.getName()));
        return "studyDetail";
    }

    @GetMapping("/studies/new")
    public String studyForm(
            @RequestParam(name = "id", required = false) Long id,
            Principal principal,
            Model model
    ) {
        model.addAttribute("editing", id != null);

        if (id != null) {
            model.addAttribute("study", studyService.findForEdit(id, principal.getName()));
        }

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