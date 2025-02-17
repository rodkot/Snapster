package ru.sbertech.platformv.print.benchmark.domain.model.dto;

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
public class CompanyDto implements Inspectable, Serializable {
    private Long id;
    private String name;
    private String email;
    private FileDto logo;
    private String code;
    private EmployeeDto director;
    private OfficeDto generalOffice;
    private List<OfficeDto> additionalOffices;

}
