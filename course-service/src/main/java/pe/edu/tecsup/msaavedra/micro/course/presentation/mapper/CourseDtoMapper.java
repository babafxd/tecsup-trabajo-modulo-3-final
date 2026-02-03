package pe.edu.tecsup.msaavedra.micro.course.presentation.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.entity.CourseEntity;
import pe.edu.tecsup.msaavedra.micro.course.presentation.dto.CourseResponse;
import pe.edu.tecsup.msaavedra.micro.course.presentation.dto.CreateCourseRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseDtoMapper {

    Course toDomain(CreateCourseRequest request);
    CourseResponse toResponse(Course course);
    List<CourseResponse> toResponseList(List<Course> courses);

}
