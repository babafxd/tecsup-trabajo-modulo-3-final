package pe.edu.tecsup.msaavedra.micro.enrollment.domain.repository;

import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {

    Optional<Enrollment> findById(Long id);
    Enrollment save(Enrollment product);
    List<Enrollment> findByCreatedBy(Long userId);
}
