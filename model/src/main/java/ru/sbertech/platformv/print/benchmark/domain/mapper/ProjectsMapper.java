package ru.sbertech.platformv.print.benchmark.domain.mapper;

import org.mapstruct.Mapper;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.ProjectDto;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Project;


@Mapper(componentModel = "spring")
public interface ProjectsMapper {
    ProjectDto projectToDto(Project project);
}
