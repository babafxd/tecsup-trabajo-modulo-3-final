package pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception;

public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}
