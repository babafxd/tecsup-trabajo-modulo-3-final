package pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.repository.EnrollmentRepository;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.persistence.entity.EnrollmentEntity;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.persistence.mapper.EnrollmentPersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    private final JpaEnrollmentRepository jpaRepository;
    private final EnrollmentPersistenceMapper mapper;

    @Override
    public Optional<Enrollment> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        log.debug("Saving enrollment course: {} , user: {}", enrollment.getCourseId(), enrollment.getUserId());
        EnrollmentEntity entity = mapper.toEntity(enrollment);
        EnrollmentEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Enrollment> findByCreatedBy(Long userId) {
        return mapper.toDomainList(jpaRepository.findByUserId(userId));
    }
}
