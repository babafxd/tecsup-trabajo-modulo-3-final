package pe.edu.tecsup.msaavedra.micro.payment.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;
import pe.edu.tecsup.msaavedra.micro.payment.domain.repository.PaymentRepository;
import pe.edu.tecsup.msaavedra.micro.payment.infraestructure.persistence.entity.PaymentEntity;
import pe.edu.tecsup.msaavedra.micro.payment.infraestructure.persistence.mapper.PaymentPersistenceMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository jpaRepository;
    private final PaymentPersistenceMapper mapper;

    @Override
    public Optional<Payment> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Payment save(Payment payment) {
        log.debug("Saving payment: {}", payment.getEnrollmentId());
        PaymentEntity entity = mapper.toEntity(payment);
        PaymentEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

}
