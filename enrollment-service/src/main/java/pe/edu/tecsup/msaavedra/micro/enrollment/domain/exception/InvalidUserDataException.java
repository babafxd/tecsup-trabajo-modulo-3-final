package pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception;

public class InvalidUserDataException extends RuntimeException {
    public InvalidUserDataException(String message) {
        super(message);
    }
}
