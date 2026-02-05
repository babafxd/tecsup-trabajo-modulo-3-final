package pe.edu.tecsup.msaavedra.micro.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception.UserNotFoundException;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.repository.EnrollmentRepository;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.UserClient;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.dto.UserDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEnrollmentByUserUseCase {

    private final EnrollmentRepository enrollmentRepository;
    private final UserClient userClient;

    public List<Enrollment> execute(Long userId) {

        // Validar que el usuario existe en userdb
        UserDTO user = userClient.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("No se puede obtener matrículas: El usuario " + userId + " no existe."));

        log.info("Fetching enrollments for user from userdb: {}", user.getFullName());

        log.debug("Executing GetEnrollmentByUserUseCase for userId: {}", userId);
        return enrollmentRepository.findByCreatedBy(userId);

    }


}
