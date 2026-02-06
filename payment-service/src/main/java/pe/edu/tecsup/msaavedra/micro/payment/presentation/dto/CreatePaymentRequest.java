package pe.edu.tecsup.msaavedra.micro.payment.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

    @NotNull(message = "enrollmentId is required")
    @Min(value = 1, message = "enrollmentId must be greater than zero")
    private Long enrollmentId;

    @NotNull(message = "amount is required")
    @Min(value = 1, message = "amount must be greater than zero")
    private BigDecimal amount;

}
