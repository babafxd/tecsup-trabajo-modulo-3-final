package pe.edu.tecsup.msaavedra.micro.enrollment.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEnrollmentRequest {


    @NotNull(message = "userId is required")
    @Min(value = 1, message = "userId must be greater than zero")
    private Long userId;

    @NotNull(message = "courseId is required")
    @Min(value = 1, message = "courseId must be greater than zero")
    private Long courseId;
}
