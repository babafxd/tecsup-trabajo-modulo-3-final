package pe.edu.tecsup.msaavedra.micro.payment.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.payment.domain.event.PaymentApprovedEvent;
import pe.edu.tecsup.msaavedra.micro.payment.domain.event.PaymentRejectedEvent;
import pe.edu.tecsup.msaavedra.micro.payment.domain.exception.EnrollmentNotFoundException;
import pe.edu.tecsup.msaavedra.micro.payment.domain.exception.InvalidPaymentDataException;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;
import pe.edu.tecsup.msaavedra.micro.payment.domain.repository.PaymentRepository;
import pe.edu.tecsup.msaavedra.micro.payment.infraestructure.client.enrollment.EnrollmentClient;
import pe.edu.tecsup.msaavedra.micro.payment.infraestructure.client.enrollment.dto.EnrollmentDTO;
import pe.edu.tecsup.msaavedra.micro.payment.shared.infraestructure.event.KafkaEventPublisher;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentUseCase {
    private final PaymentRepository paymentRepository;
    private final EnrollmentClient enrollmentClient;
    private final KafkaEventPublisher eventPublisher;

    public Payment execute(Payment payment) {

        if (!payment.isValidEnrollmentId()) {
            throw new InvalidPaymentDataException("Invalid payment data. EnrollmentId are required.");
        }

        if (!payment.isValidAmount()) {
            throw new InvalidPaymentDataException("Invalid payment data. Ammount greater than zero.");
        }

        EnrollmentDTO user = enrollmentClient.getEnrollmentById(payment.getEnrollmentId())
                .orElseThrow(() -> new EnrollmentNotFoundException("No se puede obtener el enrollment con Id:" + payment.getEnrollmentId()));

        payment.setStatus(Payment.PaymentStatus.APPROVED);
        Payment saved = paymentRepository.save(payment);
        log.info("Payment created successfully with id: {}", saved.getId());

        if (payment.isPaymentApproved()) {
            eventPublisher.publish(
                    new PaymentApprovedEvent(
                            saved.getId(),
                            saved.getEnrollmentId(),
                            saved.getAmount(),
                            saved.getStatus().toString(),
                            saved.getPaidAt()
                    )
            );

        } else {
            eventPublisher.publish(
                    new PaymentRejectedEvent(
                            saved.getId(),
                            saved.getEnrollmentId(),
                            saved.getAmount(),
                            saved.getStatus().toString(),
                            saved.getPaidAt()
                    )
            );
        }


        return saved;
    }


}
