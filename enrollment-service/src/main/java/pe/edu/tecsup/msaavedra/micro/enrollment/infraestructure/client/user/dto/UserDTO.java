package pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private String status;
    private LocalDateTime createdAt;

}
