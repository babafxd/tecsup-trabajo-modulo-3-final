package pe.edu.tecsup.msaavedra.micro.enrollment.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.msaavedra.micro.enrollment.application.service.EnrollmentApplicationService;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.presentation.dto.CreateEnrollmentRequest;
import pe.edu.tecsup.msaavedra.micro.enrollment.presentation.dto.EnrollmentResponse;
import pe.edu.tecsup.msaavedra.micro.enrollment.presentation.mapper.EnrollmentDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Slf4j
public class EnrollmentController {
    private final EnrollmentApplicationService enrollmentApplicationService;
    private final EnrollmentDtoMapper enrollmentDtoMapper;


    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(@Valid @RequestBody CreateEnrollmentRequest request) {
        log.info("REST request to create enrollment");
        Enrollment course = enrollmentDtoMapper.toDomain(request);
        Enrollment enrollment = enrollmentApplicationService.enrollmentStudent(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enrollmentDtoMapper.toResponse(enrollment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable Long id) {
        log.info("REST request to get course by id: {}", id);
        Enrollment course = enrollmentApplicationService.getEnrollment(id);
        return ResponseEntity.ok(enrollmentDtoMapper.toResponse(course));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentByUser(@PathVariable Long userId) {
        log.info("REST request to get enrollments by user: {}", userId);
        List<Enrollment> products = enrollmentApplicationService.getEnrollmentByUser(userId);
        return ResponseEntity.ok(enrollmentDtoMapper.toResponseList(products));
    }

}
