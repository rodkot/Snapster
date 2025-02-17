package ru.sbertech.platformv.print.benchmark.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResourceResolverService {
    @Autowired
    private ClassLoader resourceLoader;

    public String pathDirResource(String path) {
        Path originalPath = Paths.get(path);
        Path directoryPath = originalPath.getParent();

        return directoryPath.toString();
    }

    public String nameResource(String path) {
        Path originalPath = Paths.get(path);
        Path filename = originalPath.getFileName();

        return filename.toString();
    }

    public String readExpectedOutputResource(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(resourceLoader.getResourceAsStream(path))))) {
            for (; ; ) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line);
            }
        }

        return builder.toString();
    }

    public File getFileFromResource(String directory, String name) throws URISyntaxException {
        return getFileFromResource(directory+name);
    }

    public File getFileFromResource(String path) throws URISyntaxException {
        return new File(Objects.requireNonNull(resourceLoader.getResource(path)).toURI());
    }
}
