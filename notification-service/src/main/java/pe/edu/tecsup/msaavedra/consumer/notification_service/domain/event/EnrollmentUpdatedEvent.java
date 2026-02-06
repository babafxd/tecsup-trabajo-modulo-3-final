package pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
