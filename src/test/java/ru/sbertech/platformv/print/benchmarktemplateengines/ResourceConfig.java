package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@TestConfiguration
public class ResourceConfig {

    @Bean
    public String reportFreemarker(@Value("${templates.freemarker.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputFreemarker(@Value("${templates.freemarker.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportHttl(@Value("${templates.httl.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputHttl(@Value("${templates.httl.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportJinjava(@Value("${templates.jinjava.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputJinjava(@Value("${templates.jinjava.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportLiqp(@Value("${templates.liqp.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputLiqp(@Value("${templates.liqp.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportMustache(@Value("${templates.mustache.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputMustache(@Value("${templates.mustache.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportPebble(@Value("${templates.pebble.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputPebble(@Value("${templates.pebble.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportThymeleaf(@Value("${templates.thymeleaf.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputThymeleaf(@Value("${templates.thymeleaf.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportTrimou(@Value("${templates.trimou.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputTrimou(@Value("${templates.trimou.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String reportVelocity(@Value("${templates.velocity.report}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    @Bean
    public String outputVelocity(@Value("${templates.velocity.output}") String path) throws IOException {
        return readExpectedOutputResource(path);
    }

    private String readExpectedOutputResource(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ExpectedOutputTest.class.getResourceAsStream(path))))) {
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
