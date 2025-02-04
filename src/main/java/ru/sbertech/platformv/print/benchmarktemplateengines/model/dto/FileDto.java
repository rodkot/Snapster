package ru.sbertech.platformv.print.benchmarktemplateengines.model.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String directory;

    @JsonIgnore
    private File file;

    public String getPath(){
        return directory+name;
    }

    @JsonIgnore
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }
}
