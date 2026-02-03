package pe.edu.tecsup.msaavedra.micro.course.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.msaavedra.micro.course.application.service.CourseApplicationService;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.presentation.dto.CourseResponse;
import pe.edu.tecsup.msaavedra.micro.course.presentation.dto.CreateCourseRequest;
import pe.edu.tecsup.msaavedra.micro.course.presentation.mapper.CourseDtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseApplicationService courseApplicationService;
    private final CourseDtoMapper courseDtoMapper;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        log.info("REST request to get all courses");
        List<Course> courses = courseApplicationService.getAllCourse();
        return ResponseEntity.ok(courseDtoMapper.toResponseList(courses));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createUser(@Valid @RequestBody CreateCourseRequest request) {
        log.info("REST request to create course: {}", request.getTitle());
        Course course = courseDtoMapper.toDomain(request);
        Course createdUser = courseApplicationService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseDtoMapper.toResponse(createdUser));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Course> publishCourse(
            @PathVariable Long id) {

        Course course = courseApplicationService.publishCourse(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getProductById(@PathVariable Long id) {
        log.info("REST request to get course by id: {}", id);
        Course course = courseApplicationService.getCourse(id);
        return ResponseEntity.ok(courseDtoMapper.toResponse(course));
    }

}
