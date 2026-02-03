package pe.edu.tecsup.msaavedra.micro.course.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.domain.repository.CourseRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCourseUseCase {

    private final CourseRepository courseRepository;

    public List<Course> execute() {
        log.debug("Executing GetAllCourseUseCase");
        return courseRepository.findAll();
    }



}
