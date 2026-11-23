package team.gdgocspring.controller;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import team.gdgocspring.domain.Study;
import team.gdgocspring.dto.StudyListViewResponse;
import team.gdgocspring.dto.StudyResponse;
import team.gdgocspring.dto.StudyViewResponse;
import team.gdgocspring.service.StudyService;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import team.gdgocspring.domain.Study;
import team.gdgocspring.service.StudyService;

import java.security.Principal;

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

    @GetMapping("/studies/{id}")
    public String getStudy(@PathVariable Long id, Principal principal, Model model) {
        Study study = studyService.findById(id);
        model.addAttribute("study", new StudyViewResponse(study));

        boolean owner = principal != null
                && study.getLeaderId().equals(principal.getName());
        model.addAttribute("owner", owner);

        return "studyDetail";
    }

    @GetMapping("/studies/new")
    public String create(@RequestParam(name = "id", required = false) Long id, Principal principal, Model model) {
        model.addAttribute("editing", id != null);

        if (id != null) {
            Study study = studyService.findForEdit(id, principal.getName());
            model.addAttribute("study", study);
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
