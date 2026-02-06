package pe.edu.tecsup.msaavedra.micro.payment.infraestructure.client.enrollment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.edu.tecsup.msaavedra.micro.payment.infraestructure.client.enrollment.dto.EnrollmentDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnrollmentClient {

    private final RestTemplate restTemplate;

    @Value("${enrollment.service.url}")
    private String enrollmentServiceUrl;

    public Optional<EnrollmentDTO> getEnrollmentById(Long enrollmentId) {
        log.info("Calling Enrollment Service (PostgreSQL enrollmentDB) to get enrollment with id: {}", enrollmentId);

        String url = enrollmentServiceUrl + "/api/enrollments/" + enrollmentId;

        try {
            EnrollmentDTO enrollment = restTemplate.getForObject(url, EnrollmentDTO.class);
            if (enrollment == null) {
                log.warn("Enrollment {} not found", enrollmentId);
            }

            log.info("Enrollment retrieved successfully from enrollmentdb: {}", enrollment);
            return Optional.ofNullable(enrollment);

        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error calling Enrollment Service: {}", e.getMessage());
            throw new RuntimeException("Error calling Enrollment Service: " + e.getMessage());
        }
    }

}

