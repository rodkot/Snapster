package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import java.io.Serializable;
import java.util.List;

import liqp.parser.Inspectable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDto implements Inspectable, Serializable {
    private Long id;
    private String name;
    private String location;
    private String photo;
    private List<EmployeeDto> employees;
    private List<String> resources;
}
