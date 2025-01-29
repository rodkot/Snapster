package ru.sbertech.platformv.print.benchmarktemplateengines.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

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
}
