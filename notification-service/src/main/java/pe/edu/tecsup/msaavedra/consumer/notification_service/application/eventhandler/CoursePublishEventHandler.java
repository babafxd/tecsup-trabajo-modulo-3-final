package pe.edu.tecsup.msaavedra.consumer.notification_service.application.eventhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event.CoursePublishedEvent;
import pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event.DomainEvent;
import pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event.EnrollmentCreatedEvent;
import pe.edu.tecsup.msaavedra.consumer.notification_service.infraestructure.config.KafkaConfig;

@Slf4j
@Component
public class CoursePublishEventHandler {

    private final ObjectMapper objectMapper;

    public CoursePublishEventHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = KafkaConfig.COURSE_EVENTS_TOPIC,
            groupId = "notifications-service-group-4"
    )
    public void handleEvents(String message) throws InterruptedException {
        try {
            CoursePublishedEvent event = objectMapper.readValue(message, CoursePublishedEvent.class);
            handleCoursePublished(event);
        } catch (Exception e) {
            log.error("Error parsing Course event: {}", e.getMessage());
        }
    }

    public void handleCoursePublished(CoursePublishedEvent event) throws InterruptedException {
        log.info("[{}] Publishing course...", Thread.currentThread().getName());
        Thread.sleep(1000);
        log.info("Publishing course: {}", event.getTitle());

    }


}
