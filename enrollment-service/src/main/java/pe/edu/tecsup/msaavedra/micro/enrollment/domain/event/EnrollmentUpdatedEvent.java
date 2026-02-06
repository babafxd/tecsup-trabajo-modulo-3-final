package pe.edu.tecsup.msaavedra.micro.enrollment.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.tecsup.msaavedra.micro.enrollment.shared.domain.event.DomainEvent;

@Getter
@NoArgsConstructor
public class EnrollmentUpdatedEvent extends DomainEvent {

    private Long enrollmentId;
    private Long userId;
    private Long courseId;
    private String status;
    private String studentEmail;

    public EnrollmentUpdatedEvent(Long enrollmentId, Long userId, Long courseId, String status, String studentEmail) {
        super();
        this.enrollmentId = enrollmentId;
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
        this.studentEmail = studentEmail;
    }

    @Override
    public String getKey() {
        return this.enrollmentId.toString();
    }

}
