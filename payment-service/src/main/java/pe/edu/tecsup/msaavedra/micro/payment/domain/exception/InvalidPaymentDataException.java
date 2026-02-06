package pe.edu.tecsup.msaavedra.micro.payment.domain.exception;

public class InvalidPaymentDataException extends RuntimeException {
    public InvalidPaymentDataException(String message) {
        super(message);
    }
}
