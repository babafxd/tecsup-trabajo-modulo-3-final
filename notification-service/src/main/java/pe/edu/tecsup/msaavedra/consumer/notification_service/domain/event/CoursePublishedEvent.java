package pe.edu.tecsup.msaavedra.consumer.notification_service.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursePublishedEvent extends DomainEvent {

    private String courseId;
    private String title;
    private String description;
    private boolean published;

    public CoursePublishedEvent(String courseId, String title, String description, boolean published) {
        super();
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.published = published;
    }

    @Override
    public String getKey() {
        return this.courseId;
    }

}
