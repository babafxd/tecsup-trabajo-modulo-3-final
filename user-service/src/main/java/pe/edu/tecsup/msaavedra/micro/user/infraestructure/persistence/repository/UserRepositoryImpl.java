package pe.edu.tecsup.msaavedra.micro.user.infraestructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.msaavedra.micro.user.domain.model.User;
import pe.edu.tecsup.msaavedra.micro.user.domain.repository.UserRepository;
import pe.edu.tecsup.msaavedra.micro.user.infraestructure.persistence.mapper.UserPersistenceMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
