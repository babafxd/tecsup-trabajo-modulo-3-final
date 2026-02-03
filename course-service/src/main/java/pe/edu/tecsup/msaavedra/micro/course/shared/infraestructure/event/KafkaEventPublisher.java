package pe.edu.tecsup.msaavedra.micro.course.shared.infraestructure.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.course.domain.event.CoursePublishedEvent;
import pe.edu.tecsup.msaavedra.micro.course.shared.domain.event.DomainEvent;
import pe.edu.tecsup.msaavedra.micro.course.shared.infraestructure.config.KafkaConfig;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    private String getTopicFromEvent(DomainEvent event) {

        if ( event instanceof CoursePublishedEvent) {
            return KafkaConfig.COURSE_EVENTS_TOPIC;
        } else {
            throw new IllegalArgumentException("Unknown event type: " + event.getEventType());
        }

    }

    public void publish(DomainEvent event) {
        log.info("Publicando: {} [{}]", event.getEventType(), event.getEventId());


        String topic = getTopicFromEvent(event);
        String key = event.getKey(); // devuelva el course Id

        kafkaTemplate.send(
                topic,
                key,
                event
        );


    }
}
