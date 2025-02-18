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
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDto implements Inspectable, Serializable {
    private Long id;
    private String name;
    private String location;
    private FileDto photo;
    private List<EmployeeDto> employees;
    private List<String> resources;

    public Map<String, Object> getMap() {
        return Map.of("id", id, "name", name, "location", location, "photo", photo.getMap(), "employees",
                employees.stream().map(EmployeeDto::getMap).toList(), "resources", resources);
    }
}
