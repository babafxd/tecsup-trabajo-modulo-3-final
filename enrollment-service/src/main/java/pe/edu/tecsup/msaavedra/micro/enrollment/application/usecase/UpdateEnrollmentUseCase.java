package pe.edu.tecsup.msaavedra.micro.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.event.EnrollmentUpdatedEvent;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.EnrollmentNotFoundException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.UserNotFoundException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.repository.EnrollmentRepository;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.UserClient;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.dto.UserDTO;
import pe.edu.tecsup.msaavedra.micro.enrollment.shared.infraestructure.event.KafkaEventPublisher;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateEnrollmentUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final KafkaEventPublisher eventPublisher;
    private final UserClient userClient;

    @Transactional
    public Enrollment execute(Long id, Enrollment.EnrollmentStatus status) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("No se puede obtener el enrollment con Id:" + id));


        UserDTO user = userClient.getUserById(enrollment.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No se puede obtener el usuario con Id:" + enrollment.getUserId()));


        enrollment.setStatus(status);
        Enrollment saved = enrollmentRepository.save(enrollment);
        log.info("Enrollment updated: {}", saved.getId());

        // Publicar el evento
        eventPublisher.publish(
                new EnrollmentUpdatedEvent(
                        saved.getId(),
                        saved.getUserId(),
                        saved.getCourseId(),
                        saved.getStatus().toString(),
                        user.getEmail()
                )
        );

        return saved;
    }

}
