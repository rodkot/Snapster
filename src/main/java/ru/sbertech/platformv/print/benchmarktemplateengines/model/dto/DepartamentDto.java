package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import java.io.Serializable;

import liqp.parser.Inspectable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartamentDto implements Inspectable, Serializable {
    private Long id;
    private String name;
    private double budget;
}
