package pe.edu.tecsup.msaavedra.micro.user.presentation.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.user.domain.model.User;
import pe.edu.tecsup.msaavedra.micro.user.presentation.dto.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserResponse toResponse(User course);
}
