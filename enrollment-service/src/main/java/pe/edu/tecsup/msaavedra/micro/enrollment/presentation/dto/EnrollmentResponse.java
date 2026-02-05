package pe.edu.tecsup.msaavedra.micro.enrollment.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {

    private Long id;
    private Long userId;
    private Long courseId;
    private String status;

}
