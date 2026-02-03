package pe.edu.tecsup.msaavedra.micro.course.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.tecsup.msaavedra.micro.course.shared.domain.event.DomainEvent;

@Getter
@NoArgsConstructor
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
