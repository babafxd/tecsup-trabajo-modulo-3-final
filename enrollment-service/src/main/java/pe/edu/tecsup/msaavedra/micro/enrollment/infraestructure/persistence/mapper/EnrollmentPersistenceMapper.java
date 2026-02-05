package pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.tecsup.msaavedra.micro.enrollment.domain.model.Enrollment;
import pe.edu.tecsup.msaavedra.micro.enrollment.infraestructure.persistence.entity.EnrollmentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentPersistenceMapper {
    Enrollment toDomain(EnrollmentEntity entity);
    EnrollmentEntity toEntity(Enrollment enrollment);
    List<Enrollment> toDomainList(List<EnrollmentEntity> entities);

}
