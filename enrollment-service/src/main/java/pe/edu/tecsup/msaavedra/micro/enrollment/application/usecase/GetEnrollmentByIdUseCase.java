package pe.edu.tecsup.msaavedra.micro.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.enrollment.application.service.EnrollmentApplicationService;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.EnrollmentNotFoundException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.repository.EnrollmentRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEnrollmentByIdUseCase {

    private final EnrollmentRepository enrollmentRepository;

    public Enrollment execute(Long id){
        log.debug("Executing GetEnrollmentByIdUseCase for id: {}", id);
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
    }

}
