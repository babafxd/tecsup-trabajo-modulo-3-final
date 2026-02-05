package pe.edu.tecsup.msaavedra.micro.enrollment.domain.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
