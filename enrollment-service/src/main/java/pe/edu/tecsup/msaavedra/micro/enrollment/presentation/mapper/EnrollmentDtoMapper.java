package pe.edu.tecsup.msaavedra.micro.enrollment.presentation.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.presentation.dto.CreateEnrollmentRequest;
import pe.edu.tecsup.msaavedra.micro.enrollment.presentation.dto.EnrollmentResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentDtoMapper {

    Enrollment toDomain(CreateEnrollmentRequest request);
    EnrollmentResponse toResponse(Enrollment enrollment);
    List<EnrollmentResponse> toResponseList(List<Enrollment> enrollments);

}
