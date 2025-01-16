package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import java.util.List;

import liqp.parser.Inspectable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto implements Inspectable {
    private Long id;
    private String name;
    private String description;
    private List<DepartamentDto> departaments;
    private String status;
}
