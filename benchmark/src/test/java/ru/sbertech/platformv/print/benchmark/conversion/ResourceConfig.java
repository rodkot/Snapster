package ru.sbertech.platformv.print.benchmark.conversion;

import  org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@TestConfiguration
public class ResourceConfig {
    @Bean
    public String report(@Value("${converter.report}")
                         String path, ResourceResolverService resourceResolverService, Path resourcePath) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path).replaceAll("resources://", resourcePath.toString());
    }

    @Bean
    public Path resourcePath(ResourceLoader resourceLoader) throws IOException {
        return resourceLoader.getResource("").getFile().toPath().toAbsolutePath();
    }

    @Bean
    public File outputFlyingSaucer(@Value("${converter.flying-saucer.output}") String path) throws IOException {
        Path filePath = Paths.get(path);

        Files.createDirectories(filePath.getParent());

        if (!Files.exists(filePath)) {
            return Files.createFile(filePath).toFile();
        }

        return new File(path);
    }

    @Bean
    public File outputIText(@Value("${converter.itext.output}") String path) throws IOException {
        Path filePath = Paths.get(path);

        Files.createDirectories(filePath.getParent());

        if (!Files.exists(filePath)) {
            return Files.createFile(filePath).toFile();
        }

        return new File(path);
    }

    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }
}
