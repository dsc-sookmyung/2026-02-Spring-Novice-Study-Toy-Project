package team.gdgocspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.gdgocspring.domain.Study;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.dto.StudyResponse;
import team.gdgocspring.service.StudyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudyApiController{
    private final StudyService studyservice;

    @PostMapping("/api/studies")
    public ResponseEntity<StudyResponse> addStudy(
            @RequestBody AddStudyRequest request,
            @AuthenticationPrincipal OidcUser account) {

        Study savedStudy = studyservice.save(request, account);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StudyResponse(savedStudy));
    }

    // 전체 스터디 목록 조회 API
    @GetMapping("/api/studies")
    public ResponseEntity<List<StudyResponse>> findAllStudies() {

        // 1. Service에서 전체 스터디 조회
        List<Study> studies = studyservice.findAll();

        // 2. Study 엔티티 목록을 StudyResponse DTO 목록으로 변환
        List<StudyResponse> responses = studies.stream()
                .map(StudyResponse::new)
                .toList();

        // 3. HTTP 200 OK와 함께 조회 결과 반환
        return ResponseEntity.ok(responses);
    }
}