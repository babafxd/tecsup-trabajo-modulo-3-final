package pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.entity.CourseEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursePersistenceMapper {

    Course toDomain(CourseEntity entity);
    CourseEntity toEntity(Course course);
    List<Course> toDomainList(List<CourseEntity> entities);

}
