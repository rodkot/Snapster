package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import org.mapstruct.Mapper;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Company;

@Mapper(componentModel = "spring", uses = {OfficeMapper.class, EmployeeMapper.class, ProjectsMapper.class})
public interface CompanyMapper {
    CompanyDto companyToDto(Company company);
}
