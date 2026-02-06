package pe.edu.tecsup.msaavedra.micro.enrollment.domain.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    private Long id;
    private Long userId;
    private Long courseId;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    private LocalDateTime createdAt;

    public boolean isValidUserId() {
        return (userId > 0);
    }

    public boolean isValidCourseId() {
        return (courseId > 0);
    }


    public enum EnrollmentStatus {
        PENDING_PAYMENT, CONFIRMED, CANCELLED
    }

}
