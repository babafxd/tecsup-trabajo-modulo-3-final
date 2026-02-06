package pe.edu.tecsup.msaavedra.micro.payment.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;
    private Long enrollmentId;
    private float amount;
    private String status;
    private LocalDateTime paidAt;
}
