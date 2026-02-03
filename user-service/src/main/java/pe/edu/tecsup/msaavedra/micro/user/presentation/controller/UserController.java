package pe.edu.tecsup.msaavedra.micro.user.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.tecsup.msaavedra.micro.user.application.service.UserApplicationService;
import pe.edu.tecsup.msaavedra.micro.user.domain.model.User;
import pe.edu.tecsup.msaavedra.micro.user.presentation.dto.UserResponse;
import pe.edu.tecsup.msaavedra.micro.user.presentation.mapper.UserDtoMapper;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserApplicationService userApplicationService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.info("REST request to get user by id: {}", id);
        User user = userApplicationService.getUser(id);
        return ResponseEntity.ok(userDtoMapper.toResponse(user));
    }

}
