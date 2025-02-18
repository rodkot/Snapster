package ru.sbertech.platformv.print.benchmark.domain.model.dto;

import java.io.Serializable;
import java.util.Map;

import liqp.parser.Inspectable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Inspectable, Serializable {
    private Long id;
    private String name;
    private String position;
    private ProjectDto project;
    private double salary;
    private int experience;

    public Map<String, Object> getMap() {
        return Map.of("id", id, "name", name, "position", position, "project", project.getMap(), "salary", salary,
                "experience", experience);
    }
}
