package pe.edu.tecsup.msaavedra.micro.payment.presentation.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;
import pe.edu.tecsup.msaavedra.micro.payment.presentation.dto.CreatePaymentRequest;
import pe.edu.tecsup.msaavedra.micro.payment.presentation.dto.PaymentResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentDtoMapper {

    Payment toDomain(CreatePaymentRequest request);
    PaymentResponse toResponse(Payment payment);
    List<PaymentResponse> toResponseList(List<Payment> payments);

}
