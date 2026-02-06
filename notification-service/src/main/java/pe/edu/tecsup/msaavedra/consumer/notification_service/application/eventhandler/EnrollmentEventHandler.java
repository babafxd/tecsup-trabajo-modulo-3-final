package pe.edu.tecsup.msaavedra.consumer.notification_service.application.eventhandler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event.EnrollmentCreatedEvent;
import pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event.EnrollmentUpdatedEvent;
import pe.edu.tecsup.msaavedra.consumer.notification_service.infraestructure.config.KafkaConfig;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(
        topics = KafkaConfig.ENROLLMENT_EVENTS_TOPIC,
        groupId = "enrollments-service-group"
)
public class EnrollmentEventHandler {

    @KafkaHandler
    public void handleApproved(EnrollmentCreatedEvent event) {
        String message = "✅ Enrollment Pendiente de pago, ID " + event.getEnrollmentId();
        handleEnrollment(event.getStudentEmail(), message);
    }

    @KafkaHandler
    public void handleRejected(EnrollmentUpdatedEvent event) {

        String message = "";
        if(event.getStatus().equalsIgnoreCase(EnrollmentStatus.CONFIRMED.toString()))
        {
            message = "✅ Enrollment CONFIRMADO : ID " + event.getEnrollmentId();
        }

        if(event.getStatus().equalsIgnoreCase(EnrollmentStatus.CANCELLED.toString()))
        {
            message = "❌ Enrollment CANCELADO : ID " + event.getEnrollmentId();
        }

        handleEnrollment(event.getStudentEmail(), message);
    }

    public enum EnrollmentStatus {
        PENDING_PAYMENT, CONFIRMED, CANCELLED
    }

    @KafkaHandler(isDefault = true)
    public void handleUnknown(Object event) {
        log.warn("❓ Evento de pago desconocido o no soportado: {}", event.getClass().getName());
    }

    public void handleEnrollment(String email, String subject) {
        log.info("[{}] Sending notifications...", Thread.currentThread().getName());
        log.info("Email sent for enrollment course to: {}, subject: {}", email, subject);

    }

}
