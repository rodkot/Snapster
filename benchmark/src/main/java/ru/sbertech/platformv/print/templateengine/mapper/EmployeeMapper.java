package ru.sbertech.platformv.print.templateengine.mapper;

import org.mapstruct.Mapper;
import ru.sbertech.platformv.print.templateengine.model.EmployeeDto;
import ru.sbertech.platformv.print.templateengine.model.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto employeeToDto(Employee employee);
}
