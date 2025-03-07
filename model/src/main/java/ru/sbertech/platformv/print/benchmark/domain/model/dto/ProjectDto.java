package ru.sbertech.platformv.print.benchmark.domain.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import liqp.parser.Inspectable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto implements Inspectable, Serializable {
    private Long id;
    private String name;
    private String description;
    private List<DepartmentDto> departaments;
    private String status;
}
