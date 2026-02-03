package pe.edu.tecsup.msaavedra.micro.course.shared.infraestructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {

    public static final String COURSE_EVENTS_TOPIC = "lms.course.events";

    @Bean
    public NewTopic courseEventsTopic() {
        return new NewTopic(COURSE_EVENTS_TOPIC, // topic
                1,  // Nro particiones
                (short) 1  // Nro de replicas
        );
    }
}
