package ru.sbertech.platformv.print.benchmark.domain.model.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import liqp.parser.Inspectable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDto implements Inspectable, Serializable {
    private String name;
    private String directory;

    @JsonIgnore
    private File file;

    public String data() throws IOException {
        byte[] bytes = getInputStream().readAllBytes();

        return "data:"+ Files.probeContentType(file.toPath())+";base64,"+Base64.getEncoder().encodeToString(bytes);
    }

    public String getPath(){
        return directory+name;
    }

    @JsonIgnore
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @SneakyThrows
    public Map<String, Object> getMap(){
        return Map.of("name", name, "data", data());
    }
}
