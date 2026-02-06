package pe.edu.tecsup.msaavedra.micro.payment.infraestructure.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;
import pe.edu.tecsup.msaavedra.micro.payment.infraestructure.persistence.entity.PaymentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentPersistenceMapper {
    Payment toDomain(PaymentEntity entity);
    PaymentEntity toEntity(Payment payment);
    List<Payment> toDomainList(List<PaymentEntity> entities);


}
