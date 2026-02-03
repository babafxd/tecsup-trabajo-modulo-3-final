package pe.edu.tecsup.msaavedra.micro.user.domain.repository;

import pe.edu.tecsup.msaavedra.micro.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}
