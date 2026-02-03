package pe.edu.tecsup.msaavedra.micro.course.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.course.domain.exception.CourseNotFoundException;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.domain.repository.CourseRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetCourseByIdUseCase {
    private final CourseRepository courseRepository;

    public Course execute(Long id) {
        log.debug("Executing GetCourseByIdUseCase for id: {}", id);
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
    }
}
