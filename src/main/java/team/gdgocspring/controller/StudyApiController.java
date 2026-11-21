package team.gdgocspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.gdgocspring.domain.Study;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.service.StudyService;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class StudyApiController {

    private final StudyService studyService;

    @PostMapping("/api/studies")
    public ResponseEntity<Study> addStudy(@RequestBody AddStudyRequest request, Principal principal) {
        Study savedStudy = studyService.save(request, principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedStudy);
    }

    @DeleteMapping("/api/studies/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable("id") long id,
                                            Principal principal) {
        studyService.delete(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
