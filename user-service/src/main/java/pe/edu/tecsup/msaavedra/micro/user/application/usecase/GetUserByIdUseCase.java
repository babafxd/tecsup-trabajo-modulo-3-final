package pe.edu.tecsup.msaavedra.micro.user.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.msaavedra.micro.user.domain.exception.UserNotFoundException;
import pe.edu.tecsup.msaavedra.micro.user.domain.model.User;
import pe.edu.tecsup.msaavedra.micro.user.domain.repository.UserRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetUserByIdUseCase {

    private final UserRepository userRepository;
    public User execute(Long id){
        log.debug("Executing GetUserByIdUseCase for id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

}
