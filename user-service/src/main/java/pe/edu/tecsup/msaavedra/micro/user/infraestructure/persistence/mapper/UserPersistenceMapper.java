package pe.edu.tecsup.msaavedra.micro.user.infraestructure.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.user.domain.model.User;
import pe.edu.tecsup.msaavedra.micro.user.infraestructure.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User user);

}
