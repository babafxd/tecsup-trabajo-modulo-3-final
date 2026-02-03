package pe.edu.tecsup.msaavedra.micro.course.domain.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
