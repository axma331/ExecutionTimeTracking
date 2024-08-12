package t1.ismailov.timetracking.mapper;

import org.aspectj.lang.Signature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import t1.ismailov.timetracking.dto.MethodDto;
import t1.ismailov.timetracking.entity.Method;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MethodMapper {

    Method dtoToEntity(MethodDto dto);

    @Mapping(target = "name", expression = "java(signature.getName())")
    @Mapping(target = "declaredType", expression = "java(signature.getDeclaringType().getSimpleName())")
    @Mapping(target = "group", expression = "java(signature.getDeclaringType().getPackageName()" +
            ".substring(signature.getDeclaringType().getPackageName().lastIndexOf('.') + 1))")
    MethodDto getMethodDtoFromSignature(Signature signature);
}
