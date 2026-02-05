package pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
