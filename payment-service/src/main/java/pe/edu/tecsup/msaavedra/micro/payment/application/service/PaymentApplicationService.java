package pe.edu.tecsup.msaavedra.micro.payment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.msaavedra.micro.payment.application.usecase.CreatePaymentUseCase;
import pe.edu.tecsup.msaavedra.micro.payment.application.usecase.GetPaymentByIdUseCase;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentApplicationService {

    private final GetPaymentByIdUseCase getPaymentByIdUseCase;
    private final CreatePaymentUseCase createPaymentUseCase;

    @Transactional
    public Payment payment(Payment payment) {
        return createPaymentUseCase.execute(payment);
    }

    @Transactional(readOnly = true)
    public Payment getPayment(Long id) {
        return getPaymentByIdUseCase.execute(id);
    }

}
