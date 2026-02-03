package pe.edu.tecsup.msaavedra.micro.course.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.course.domain.exception.InvalidCourseDataException;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.domain.repository.CourseRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCourseUseCase {

    private final CourseRepository courseRepository;

    public Course execute(Course course) {

        if (!course.isValid()) {
            throw new InvalidCourseDataException("Invalid course data. Title are required.");
        }

        Course savedCourse = courseRepository.save(course);
        log.info("Course created successfully with id: {}", savedCourse.getId());

        return savedCourse;

    }

}
