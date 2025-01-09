package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OfficeDto {
    private Long id;
    private String name;
    private String location;
    private List<EmployeeDto> employees;
    private List<String> resources;
}
