package pe.edu.tecsup.msaavedra.micro.payment.domain.repository;

import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;

import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> findById(Long id);
    Payment save(Payment payment);
}
