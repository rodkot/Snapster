package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartamentDto {
    private Long id;
    private String name;
    private double budget;
}
