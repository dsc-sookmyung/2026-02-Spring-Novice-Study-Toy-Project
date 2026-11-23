package team.gdgocspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.gdgocspring.domain.Study;
import team.gdgocspring.dto.AddStudyRequest;
import team.gdgocspring.dto.StudyResponse;
import team.gdgocspring.dto.UpdateStudyRequest;
import team.gdgocspring.service.StudyService;

import java.security.Principal;
import java.util.List;

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

    // 스터디 모집글 목록 조회 컨트롤러 메서드
    @GetMapping("/api/studies")
    public ResponseEntity<List<StudyResponse>> findAllStudies() {
        List<StudyResponse> studies = studyService.findAll()
                .stream()
                .map(StudyResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(studies);
    }

    // 스터디 모집글 조회 컨트롤러 메서드
    @GetMapping("/api/studies/{id}")
    public ResponseEntity<StudyResponse> findStudy(@PathVariable long id) {
        Study study = studyService.findById(id);

        return ResponseEntity.ok()
                .body(new StudyResponse(study));
    }

    @DeleteMapping("/api/studies/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable("id") long id,
                                            Principal principal) {
        studyService.delete(id, principal.getName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/studies/{id}")
    public ResponseEntity<Study> updateStudy(@PathVariable("id") long id, @RequestBody UpdateStudyRequest request, Principal principal) {
        Study updatedStudy = studyService.update(id, request, principal.getName());

        return ResponseEntity.ok()
                .body(updatedStudy);
    }
}
