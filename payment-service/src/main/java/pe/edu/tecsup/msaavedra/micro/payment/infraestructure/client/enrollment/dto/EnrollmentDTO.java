package pe.edu.tecsup.msaavedra.micro.payment.infraestructure.client.enrollment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {

    private Long id;
    private Long userId;
    private Long courseId;
    private String status;

}
