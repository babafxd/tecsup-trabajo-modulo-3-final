package pe.edu.tecsup.msaavedra.micro.course.domain.exception;

public class InvalidCourseDataException extends RuntimeException {
    public InvalidCourseDataException(String message) {
        super(message);
    }
}
