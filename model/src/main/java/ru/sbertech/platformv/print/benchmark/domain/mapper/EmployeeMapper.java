package ru.sbertech.platformv.print.benchmark.domain.mapper;

import org.mapstruct.Mapper;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.EmployeeDto;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto employeeToDto(Employee employee);
}
