package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

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
public class FileDto implements Inspectable, Serializable {
    private String name;
}
