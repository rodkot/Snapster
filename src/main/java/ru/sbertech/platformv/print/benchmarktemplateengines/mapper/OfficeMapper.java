package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Employee;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Office;


@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, ProjectsMapper.class})
public interface OfficeMapper {
    OfficeDto officeToDto(Office office);
}
