package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import java.io.Serializable;

import liqp.parser.Inspectable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Project;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto implements Inspectable, Serializable {
    private Long id;
    private String name;
    private String position;
    private Project project;
    private double salary;
    private int experience;
}
