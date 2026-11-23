package team.gdgocspring.controller;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.gdgocspring.domain.Study;
import team.gdgocspring.domain.User;
import team.gdgocspring.service.StudyService;
import team.gdgocspring.service.UserService;

import java.util.List;

@Controller
public class StudyViewController {
    private final ObjectProvider<ClientRegistrationRepository> registrations;
    private final StudyService studyService;
    private final UserService userService;
    public StudyViewController(ObjectProvider<ClientRegistrationRepository> registrations, StudyService studyService, UserService userService) {
        this.registrations = registrations;
        this.studyService = studyService;
        this.userService = userService;
    }

    @GetMapping({"/", "/studies"})
    public String list(Model model) {
        List<Study> studyList = studyService.getAllStudies();
        model.addAttribute("studies", studyList);
        return "studyList";
    }

    @GetMapping("/studies/{id}")
    public String detail(@PathVariable Integer id, @AuthenticationPrincipal OidcUser account, Model model) {
        Study study = studyService.getStudy(id);
        boolean isOwner = account != null && study.getOwner().getGoogleSubject().equals(account.getSubject());

        model.addAttribute("study", study);
        model.addAttribute("isOwner", isOwner);//스터디장에게만 수정 삭제 버튼 보임
        return "studyDetail";
    }

    @GetMapping("/studies/new")
    public String create(Model model) {
        model.addAttribute("editing", false);
        return "studyForm";
    }

    @GetMapping("/studies/{id}/edit")
    public String edit(@PathVariable Integer id,
                       @AuthenticationPrincipal OidcUser account,
                       Model model) {
        Study study = studyService.getStudy(id);
        User currentUser = userService.getUser(account.getSubject());

        // URL 직접 입력으로 남의 수정 페이지에 들어오는 것 차단
        if (!study.getOwner().getGoogleSubject().equals(currentUser.getGoogleSubject())) {
            throw new IllegalArgumentException("스터디장만 수정할 수 있습니다.");
        }

        model.addAttribute("editing", true);
        model.addAttribute("study", study);
        return "studyForm";
    }
    //수정 처리 메서드
    @PostMapping("/studies/{id}/edit")
    public String updateStudy(@PathVariable Integer id,
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam int capacity,
                              @AuthenticationPrincipal OidcUser account) {
        User currentUser = userService.getUser(account.getSubject());
        studyService.updateStudy(id, title, description, capacity, currentUser);
        return "redirect:/studies/" + id;
    }
    //삭제 처리 메서드
    @PostMapping("/studies/{id}/delete")
    public String deleteStudy(@PathVariable Integer id,
                              @AuthenticationPrincipal OidcUser account) {
        User currentUser = userService.getUser(account.getSubject());
        studyService.deleteStudy(id, currentUser);
        return "redirect:/studies";
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

    @PostMapping("/studies")
    public String registerStudy(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam int capacity,
            @AuthenticationPrincipal OidcUser account
    ) {
        User owner = userService.getUser(account.getSubject());
        Study savedStudy = studyService.createStudy(title, description, capacity, owner);
        return "redirect:/studies/" + savedStudy.getStudyId();
    }
}
