package pe.edu.tecsup.msaavedra.consumer.notification_service.infraestructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {

    public static final String ENROLLMENT_EVENTS_TOPIC = "lms.enrollment.events";
    public static final String COURSE_EVENTS_TOPIC = "lms.course.events";

    @Bean
    public NewTopic courseEventsTopic() {
        return new NewTopic(COURSE_EVENTS_TOPIC, // topic
                1,  // Nro particiones
                (short) 1  // Nro de replicas
        );
    }

    @Bean
    public NewTopic enrollmentEventsTopic() {
        return new NewTopic(ENROLLMENT_EVENTS_TOPIC, // topic
                1,  // Nro particiones
                (short) 1  // Nro de replicas
        );
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registro del módulo para manejar LocalDateTime (Java 8)
        mapper.registerModule(new JavaTimeModule());
        // Evita que falle si el JSON tiene campos que tu Java no tiene
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
