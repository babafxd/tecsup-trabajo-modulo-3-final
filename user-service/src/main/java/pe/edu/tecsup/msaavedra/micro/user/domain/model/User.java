package pe.edu.tecsup.msaavedra.micro.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private Long id;
    private String fullName;
    private String email;
    private String status;
    private LocalDateTime createdAt;

    public boolean isValid() {
        return fullName != null && !fullName.trim().isEmpty();
    }

}
