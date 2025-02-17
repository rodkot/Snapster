package ru.sbertech.platformv.print.benchmark.domain.model.dto;

import java.io.Serializable;

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
}
