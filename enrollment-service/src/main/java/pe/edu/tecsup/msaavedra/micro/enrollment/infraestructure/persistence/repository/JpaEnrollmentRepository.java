package pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.persistence.entity.EnrollmentEntity;

import java.util.List;

public interface JpaEnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    List<EnrollmentEntity> findByUserId(Long userId);

}
