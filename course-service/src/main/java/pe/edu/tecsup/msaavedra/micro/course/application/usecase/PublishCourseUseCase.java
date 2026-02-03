package pe.edu.tecsup.msaavedra.micro.course.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.msaavedra.micro.course.domain.event.CoursePublishedEvent;
import pe.edu.tecsup.msaavedra.micro.course.domain.exception.CourseNotFoundException;
import pe.edu.tecsup.msaavedra.micro.course.domain.model.Course;
import pe.edu.tecsup.msaavedra.micro.course.domain.repository.CourseRepository;
import pe.edu.tecsup.msaavedra.micro.course.shared.infraestructure.event.KafkaEventPublisher;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublishCourseUseCase {
    private final CourseRepository courseRepository;
    private final KafkaEventPublisher eventPublisher;

    @Transactional
    public Course execute(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        course.setPublished(true);
        Course saved = courseRepository.save(course);

        log.info("Course published: {}", saved.getId());

        // Publicar el evento
        eventPublisher.publish(
                new CoursePublishedEvent(
                        saved.getId().toString(),
                        saved.getTitle(),
                        saved.getDescription(),
                        saved.isPublished()
                )
        );

        return saved;

    }

}
