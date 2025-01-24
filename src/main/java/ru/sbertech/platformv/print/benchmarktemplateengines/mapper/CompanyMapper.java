package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Company;

@Mapper(componentModel = "spring", uses = {OfficeMapper.class, EmployeeMapper.class, ProjectsMapper.class})
public interface CompanyMapper {
    @Mapping(target = "logoImage", ignore = true )
    CompanyDto companyToDto(Company company);
}
