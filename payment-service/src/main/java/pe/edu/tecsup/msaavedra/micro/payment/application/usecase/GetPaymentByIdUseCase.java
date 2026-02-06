package pe.edu.tecsup.msaavedra.micro.payment.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.payment.domain.exception.PaymentNotFoundException;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;
import pe.edu.tecsup.msaavedra.micro.payment.domain.repository.PaymentRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetPaymentByIdUseCase {

    private final PaymentRepository paymentRepository;

    public Payment execute(Long id) {
        log.debug("Executing GetPaymentByIdUseCase for id: {}", id);
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }


}
