package pe.edu.tecsup.msaavedra.consumer.notification_service.application.eventhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event.DomainEvent;
import pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event.EnrollmentCreatedEvent;
import pe.edu.tecsup.msaavedra.consumer.notification_service.infraestructure.config.KafkaConfig;

@Slf4j
@Component
public class EnrollmentEventHandler {

    private final ObjectMapper objectMapper;

    public EnrollmentEventHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = KafkaConfig.ENROLLMENT_EVENTS_TOPIC,
            groupId = "enrollments-service-group"
    )
    public void handleEvents(String message) throws InterruptedException {
        try {
            EnrollmentCreatedEvent event = objectMapper.readValue(message, EnrollmentCreatedEvent.class);
            handleEnrollment(event);
        } catch (Exception e) {
            log.error("Error parsing Enrollment event: {}", e.getMessage());
        }
    }

    public void handleEnrollment(EnrollmentCreatedEvent event) throws InterruptedException {
        log.info("[{}] Sending notifications...", Thread.currentThread().getName());
        Thread.sleep(1000);
        log.info("Email sent for enrollment course to: {}", event.getStudentEmail());

    }

}
