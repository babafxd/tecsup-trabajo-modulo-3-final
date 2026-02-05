package pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.course.dto.CourseDTO;


import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourseClient {
    private final RestTemplate restTemplate;

    @Value("${course.service.url}")
    private String courseServiceUrl;

    public Optional<CourseDTO> getCourseById(Long courseId) {
        log.info("Calling Course Service (PostgreSQL coursedb) to get user with id: {}", courseId);

        String url = courseServiceUrl + "/api/courses/" + courseId;

        try {
            CourseDTO course = restTemplate.getForObject(url, CourseDTO.class);
            if (course == null) {
                log.warn("Curso {} no encontrado", courseId);
            }

            log.info("Course retrieved successfully from coursedb: {}", course);
            return Optional.ofNullable(course);

        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error calling Course Service: {}", e.getMessage());
            throw new RuntimeException("Error calling Course Service: " + e.getMessage());
        }
    }

}
