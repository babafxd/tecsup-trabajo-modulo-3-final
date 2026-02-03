package pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.entity.CourseEntity;

public interface JpaCourseRepository extends JpaRepository<CourseEntity, Long> {
}
