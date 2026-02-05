package pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.client.user.dto.UserDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserClient {
    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public Optional<UserDTO> getUserById(Long userId) {
        log.info("Calling User Service (PostgreSQL userdb) to get user with id: {}", userId);

        String url = userServiceUrl + "/api/users/" + userId;

        try {
            UserDTO user = restTemplate.getForObject(url, UserDTO.class);
            if (user == null) {
                log.warn("Usuario {} no encontrado", userId);
            }

            log.info("User retrieved successfully from userdb: {}", user);
            return Optional.ofNullable(user);

        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error calling User Service: {}", e.getMessage());
            throw new RuntimeException("Error calling User Service: " + e.getMessage());
        }
    }

}
