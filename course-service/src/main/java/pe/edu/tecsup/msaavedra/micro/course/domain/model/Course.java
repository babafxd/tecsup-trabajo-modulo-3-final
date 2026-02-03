package pe.edu.tecsup.msaavedra.micro.course.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long id;
    private String title;
    private String description;
    private boolean published;
    private LocalDateTime createdAt;

    public boolean isValid() {
        return title != null && !title.trim().isEmpty();
    }

}
