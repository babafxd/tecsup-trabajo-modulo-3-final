package pe.edu.tecsup.msaavedra.micro.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.msaavedra.micro.user.application.usecase.GetUserByIdUseCase;
import pe.edu.tecsup.msaavedra.micro.user.domain.model.User;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserApplicationService {

    private final GetUserByIdUseCase getUserByIdUseCase;

    @Transactional(readOnly = true)
    public User getUser(Long courseId) {
        return getUserByIdUseCase.execute(courseId);
    }

}
