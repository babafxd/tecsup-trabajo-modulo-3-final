package pe.edu.tecsup.msaavedra.micro.enrollment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.msaavedra.micro.enrollment.application.usecase.EnrollmentStudenUseCase;
import pe.edu.tecsup.msaavedra.micro.enrollment.application.usecase.GetEnrollmentByIdUseCase;
import pe.edu.tecsup.msaavedra.micro.enrollment.application.usecase.GetEnrollmentByUserUseCase;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentApplicationService {

    private final EnrollmentStudenUseCase enrollmentStudenUseCase;
    private final GetEnrollmentByIdUseCase getEnrollmentByIdUseCase;
    private final GetEnrollmentByUserUseCase getEnrollmentByUserUseCase;

    @Transactional
    public Enrollment enrollmentStudent(Enrollment enrollment) {
        return enrollmentStudenUseCase.execute(enrollment);
    }

    @Transactional(readOnly = true)
    public Enrollment getEnrollment(Long id) {
        return getEnrollmentByIdUseCase.execute(id);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> getEnrollmentByUser(Long userId) {
        return getEnrollmentByUserUseCase.execute(userId);
    }

}
