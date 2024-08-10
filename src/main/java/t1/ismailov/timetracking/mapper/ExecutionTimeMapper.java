package t1.ismailov.timetracking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import t1.ismailov.timetracking.dto.ExecutionTimeDto;
import t1.ismailov.timetracking.dto.MethodDto;
import t1.ismailov.timetracking.entity.ExecutionTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExecutionTimeMapper {
    ExecutionTime dtoToEntity(ExecutionTimeDto dto);

    @Mapping(target = "executionTime", constant = "0L")
    @Mapping(target = "async", constant = "true")
    @Mapping(target = "successfully", constant = "false")
    ExecutionTimeDto createWithExceptionAsyncDto(MethodDto methodDto);

    @Mapping(target = "executionTime", constant = "0L")
    @Mapping(target = "async", constant = "false")
    @Mapping(target = "successfully", constant = "false")
    ExecutionTimeDto createWithExceptionDto(MethodDto methodDto);

    @Mapping(target = "async", constant = "true")
    @Mapping(target = "successfully", constant = "true")
    ExecutionTimeDto createAsyncDto(MethodDto methodDto, long executionTime);

    @Mapping(target = "async", constant = "false")
    @Mapping(target = "successfully", constant = "true")
    ExecutionTimeDto createDto(MethodDto methodDto, long executionTime);
}
