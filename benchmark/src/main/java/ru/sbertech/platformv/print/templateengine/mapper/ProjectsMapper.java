package ru.sbertech.platformv.print.templateengine.mapper;

import org.mapstruct.Mapper;
import ru.sbertech.platformv.print.templateengine.model.ProjectDto;
import ru.sbertech.platformv.print.templateengine.model.entity.Project;


@Mapper(componentModel = "spring")
public interface ProjectsMapper {
    ProjectDto projectToDto(Project project);
}
