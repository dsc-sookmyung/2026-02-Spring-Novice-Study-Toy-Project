package team.gdgocspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import team.gdgocspring.service.StudyService;

@RestController
@RequestMapping("/api/studies")
public class StudyApiController {

    private final StudyService studyService;

    public StudyApiController(StudyService studyService) {
        this.studyService = studyService;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id,
                       @AuthenticationPrincipal OidcUser oidcUser) {
        studyService.delete(id, oidcUser.getSubject());
    }
}