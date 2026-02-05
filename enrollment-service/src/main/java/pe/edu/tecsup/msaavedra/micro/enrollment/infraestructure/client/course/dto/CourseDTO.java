package pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private String title;
    private String description;
    private boolean published;
    private LocalDateTime createdAt;


}
