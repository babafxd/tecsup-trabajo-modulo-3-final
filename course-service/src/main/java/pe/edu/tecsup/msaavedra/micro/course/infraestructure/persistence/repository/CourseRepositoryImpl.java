package pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.domain.repository.CourseRepository;
import pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.entity.CourseEntity;
import pe.edu.tecsup.msaavedra.micro.course.infraestructure.persistence.mapper.CoursePersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CourseRepositoryImpl implements CourseRepository {


    private final JpaCourseRepository jpaRepository;
    private final CoursePersistenceMapper mapper;

    @Override
    public List<Course> findAll() {
        return mapper.toDomainList(jpaRepository.findAll());
    }

    @Override
    public Optional<Course> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Course save(Course course) {
        log.debug("Saving course: {}", course.getTitle());
        CourseEntity entity = mapper.toEntity(course);
        CourseEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Deleting course by id: {}", id);
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
