package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import java.net.URISyntaxException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.FileDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Office;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;


@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, ProjectsMapper.class})
public abstract class OfficeMapper {
    @Autowired
    private ResourceResolverService resourceResolverService;

    @Value("${templates.assets.office}")
    private String directoryPhotoOffice;

    @Mapping(source = "photo", target = "photo", qualifiedByName = "photoToFileDto")
   public abstract OfficeDto officeToDto(Office office);

    @Named("photoToFileDto")
    public FileDto stringToFileDto(String name) throws URISyntaxException {
        return new FileDto(name, directoryPhotoOffice,
                resourceResolverService.getFileFromResource(directoryPhotoOffice,name));
    }
}
