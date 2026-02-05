package pe.edu.tecsup.msaavedra.consumer.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("Application is ready to receive messages from Kafka");
        System.out.println("----------------------------------------------------------------");
    }

}
