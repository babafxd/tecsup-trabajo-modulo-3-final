package pe.edu.tecsup.msaavedra.micro.user.infraestructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.msaavedra.micro.user.infraestructure.persistence.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
