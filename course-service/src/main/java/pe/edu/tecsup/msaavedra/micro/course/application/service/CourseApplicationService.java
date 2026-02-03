package pe.edu.tecsup.msaavedra.micro.course.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.msaavedra.micro.course.application.usecase.CreateCourseUseCase;
import pe.edu.tecsup.msaavedra.micro.course.application.usecase.GetAllCourseUseCase;
import pe.edu.tecsup.msaavedra.micro.course.application.usecase.GetCourseByIdUseCase;
import pe.edu.tecsup.msaavedra.micro.course.application.usecase.PublishCourseUseCase;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseApplicationService {


    private final CreateCourseUseCase createCourseUseCase;
    private final GetAllCourseUseCase getAllCourseUseCase;
    private final PublishCourseUseCase publishCourseUseCase;
    private final GetCourseByIdUseCase getCourseByIdUseCase;

    @Transactional
    public Course createCourse(Course course) {
        return createCourseUseCase.execute(course);
    }

    @Transactional(readOnly = true)
    public List<Course> getAllCourse() {
        return getAllCourseUseCase.execute();
    }

    @Transactional
    public Course publishCourse(Long courseId) {
        return publishCourseUseCase.execute(courseId);
    }

    @Transactional(readOnly = true)
    public Course getCourse(Long courseId) {
        return getCourseByIdUseCase.execute(courseId);
    }

}
