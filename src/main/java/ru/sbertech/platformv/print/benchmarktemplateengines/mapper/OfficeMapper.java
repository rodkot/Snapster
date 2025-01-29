package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.FileDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Employee;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Office;


@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, ProjectsMapper.class})
public abstract class OfficeMapper {

    @Mapping(source = "photo", target = "photo", qualifiedByName = "photoToFileDto")
   public abstract OfficeDto officeToDto(Office office);

    @Named("photoToFileDto")
    public FileDto stringToFileDto(String file){
        return new FileDto(file);
    }
}
