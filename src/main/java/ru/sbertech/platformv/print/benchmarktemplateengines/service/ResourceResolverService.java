package ru.sbertech.platformv.print.benchmarktemplateengines.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public String readResourceFile(String path) throws IOException {
        try (InputStream inputStream = resourceLoader.getResourceAsStream(path)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                return content.toString();
            }
        }
    }
}
