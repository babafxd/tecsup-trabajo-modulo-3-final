package pe.edu.tecsup.msaavedra.micro.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.event.EnrollmentCreatedEvent;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.CourseNotFoundException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.InvalidCourseDataException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.InvalidUserDataException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.UserNotFoundException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.repository.EnrollmentRepository;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.course.CourseClient;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.course.dto.CourseDTO;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.UserClient;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.dto.UserDTO;
import pe.edu.tecsup.msaavedra.micro.enrollment.shared.infraestructure.event.KafkaEventPublisher;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnrollmentStudenUseCase {

    private final UserClient userClient;
    private final CourseClient courseClient;
    private final EnrollmentRepository enrollmentRepository;
    private final KafkaEventPublisher eventPublisher;

    @Transactional
    public Enrollment execute(Enrollment enrollment) {

        if(!enrollment.isValidCourseId()){
            throw new InvalidCourseDataException("Invalid course data. CourseId are required.");
        }

        if(!enrollment.isValidUserId()){
            throw new InvalidUserDataException("Invalid user data. UserId are required.");
        }

        UserDTO user = userClient.getUserById(enrollment.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No se puede obtener el usuario con Id:" + enrollment.getUserId()));

        CourseDTO course = courseClient.getCourseById(enrollment.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("No se puede obtener el curso con Id:" + enrollment.getCourseId()));

        enrollment.setStatus(Enrollment.EnrollmentStatus.PENDING_PAYMENT);
        Enrollment saved = enrollmentRepository.save(enrollment);

        log.info("Enrollment published: {}", saved.getId());


        // Publicar el evento
        eventPublisher.publish(
                new EnrollmentCreatedEvent(
                        saved.getId(),
                        saved.getUserId(),
                        user.getFullName(),
                        user.getEmail(),
                        saved.getCourseId(),
                        course.getTitle(),
                        saved.getStatus().toString()
                )
        );

        return saved;
    }

}
