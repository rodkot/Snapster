package ru.sbertech.platformv.print.benchmarktemplateengines.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.ProjectDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectsMapper {
    ProjectDto projectToDto(Project project);
}
