package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import liqp.parser.Inspectable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartamentDto implements Inspectable {
    private Long id;
    private String name;
    private double budget;
}
