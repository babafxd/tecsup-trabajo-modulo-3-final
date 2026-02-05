package pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
