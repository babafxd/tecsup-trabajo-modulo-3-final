package pe.edu.tecsup.msaavedra.micro.payment.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.msaavedra.micro.payment.infraestructure.persistence.entity.PaymentEntity;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
