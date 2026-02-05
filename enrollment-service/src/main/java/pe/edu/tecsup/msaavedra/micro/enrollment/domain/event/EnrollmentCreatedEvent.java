package pe.edu.tecsup.msaavedra.micro.enrollment.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.shared.domain.event.DomainEvent;

@Getter
@NoArgsConstructor
public class EnrollmentCreatedEvent extends DomainEvent {

    private Long enrollmentId;
    private Long userId;
    private String studentName;
    private String studentEmail;
    private Long courseId;
    private String courseName;
    private String status;

    public EnrollmentCreatedEvent(Long enrollmentId, Long userId, String studentName,String studentEmail, Long courseId, String courseName, String status) {
        super();
        this.enrollmentId = enrollmentId;
        this.userId = userId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }

    @Override
    public String getKey() {
        return this.enrollmentId.toString();
    }
}
