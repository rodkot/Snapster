package ru.sbertech.platformv.print.benchmark.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.FileDto;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Office;
import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;

import java.net.URISyntaxException;

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
