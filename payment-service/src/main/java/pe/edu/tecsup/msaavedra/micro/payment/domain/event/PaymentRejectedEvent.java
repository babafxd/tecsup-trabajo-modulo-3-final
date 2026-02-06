package pe.edu.tecsup.msaavedra.micro.payment.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.tecsup.msaavedra.micro.payment.shared.domain.event.DomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PaymentRejectedEvent extends DomainEvent {

    private Long paymentId;
    private Long enrollmentId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime paidAt;

    public PaymentRejectedEvent(Long paymentId, Long enrollmentId, BigDecimal amount,String status, LocalDateTime paidAt) {
        super();
        this.paymentId = paymentId;
        this.enrollmentId = enrollmentId;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
    }

    @Override
    public String getKey() {
        return this.paymentId.toString();
    }

}
