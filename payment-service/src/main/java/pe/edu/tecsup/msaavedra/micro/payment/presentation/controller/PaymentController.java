package pe.edu.tecsup.msaavedra.micro.payment.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.msaavedra.micro.payment.application.service.PaymentApplicationService;
import pe.edu.tecsup.msaavedra.micro.payment.domain.model.Payment;
import pe.edu.tecsup.msaavedra.micro.payment.presentation.dto.CreatePaymentRequest;
import pe.edu.tecsup.msaavedra.micro.payment.presentation.dto.PaymentResponse;
import pe.edu.tecsup.msaavedra.micro.payment.presentation.mapper.PaymentDtoMapper;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentApplicationService paymentApplicationService;
    private final PaymentDtoMapper paymentDtoMapper;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request) {
        log.info("REST request to create payment");
        Payment payment = paymentDtoMapper.toDomain(request);
        Payment saved = paymentApplicationService.payment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDtoMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id) {
        log.info("REST request to get payment by id: {}", id);
        Payment payment = paymentApplicationService.getPayment(id);
        return ResponseEntity.ok(paymentDtoMapper.toResponse(payment));
    }

}
