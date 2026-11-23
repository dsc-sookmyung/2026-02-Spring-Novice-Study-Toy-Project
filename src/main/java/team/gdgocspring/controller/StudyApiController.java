package team.gdgocspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.gdgocspring.domain.Study;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.service.StudyService;

@RestController
@RequiredArgsConstructor
public class StudyApiController{
    private final StudyService studyservice;

    @PostMapping("/api/studies")
    public ResponseEntity<Study> addStudy(@RequestBody AddStudyRequest request, @AuthenticationPrincipal OidcUser account){

        Study savedStudy = studyservice.save(request, account);

        //HTTP 응답
        //상태코드(Status), 헤더(Headers), 본문(Body)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedStudy);

    }

}