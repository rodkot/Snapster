package ru.sbertech.platformv.print.benchmark.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.FileDto;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Company;
import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;

import java.net.URISyntaxException;


@Mapper(componentModel = "spring", uses = {OfficeMapper.class, EmployeeMapper.class, ProjectsMapper.class})
public abstract class CompanyMapper {
    @Autowired
    private ResourceResolverService resourceResolverService;

    @Value("${templates.assets.company}")
    private String directoryLogoCompanies;

    @Mapping(source = "logo", target = "logo", qualifiedByName = "logoToFileDto")
    public abstract CompanyDto companyToDto(Company company);

    @Named("logoToFileDto")
    public FileDto stringToFileDto(String name) throws URISyntaxException {
        return new FileDto(name, directoryLogoCompanies,
                resourceResolverService.getFileFromResource(directoryLogoCompanies, name));
    }
}
