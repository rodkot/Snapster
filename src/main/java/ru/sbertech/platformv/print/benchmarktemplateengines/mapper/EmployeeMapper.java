package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import org.mapstruct.Mapper;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.EmployeeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto employeeToDto(Employee employee);
}
