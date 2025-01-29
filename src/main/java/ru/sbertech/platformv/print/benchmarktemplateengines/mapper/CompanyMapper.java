package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.FileDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Company;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;

@Mapper(componentModel = "spring", uses = {OfficeMapper.class, EmployeeMapper.class, ProjectsMapper.class})
public abstract class CompanyMapper {

    @Mapping(source = "logo", target = "logo", qualifiedByName = "logoToFileDto")
    public abstract CompanyDto companyToDto(Company company);

    @Named("logoToFileDto")
    public FileDto stringToFileDto(String file){
        return new FileDto(file);
    }
}
