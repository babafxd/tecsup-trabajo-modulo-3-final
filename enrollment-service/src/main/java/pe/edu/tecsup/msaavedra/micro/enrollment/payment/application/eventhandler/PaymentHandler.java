package pe.edu.tecsup.msaavedra.micro.enrollment.payment.application.eventhandler;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.enrollment.application.service.EnrollmentApplicationService;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.event.PaymentApprovedEvent;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.event.PaymentRejectedEvent;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.shared.infraestructure.config.KafkaConfig;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(
        topics = KafkaConfig.PAYMENT_EVENTS_TOPIC,
        groupId = "enrollment-payment-group"
)
public class PaymentHandler {

    private final EnrollmentApplicationService enrollmentApplicationService;

    @KafkaHandler
    public void handleApproved(PaymentApprovedEvent event) {
        log.info("✅ Pago Aprobado recibido: ID {} para la matrícula {}", event.getPaymentId(), event.getEnrollmentId());
        Enrollment saved = enrollmentApplicationService.updateEnrollment(event.getEnrollmentId(), Enrollment.EnrollmentStatus.CONFIRMED);

    }

    @KafkaHandler
    public void handleRejected(PaymentRejectedEvent event) {
        log.error("❌ Pago Rechazado recibido: ID {} para la matrícula {}", event.getPaymentId(), event.getEnrollmentId());
        Enrollment saved = enrollmentApplicationService.updateEnrollment(event.getEnrollmentId(), Enrollment.EnrollmentStatus.CANCELLED);
    }

    @KafkaHandler(isDefault = true)
    public void handleUnknown(Object event) {
        log.warn("❓ Evento de pago desconocido o no soportado: {}", event.getClass().getName());
    }

}
