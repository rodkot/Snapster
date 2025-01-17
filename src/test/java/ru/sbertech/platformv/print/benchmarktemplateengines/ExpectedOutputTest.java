package ru.sbertech.platformv.print.benchmarktemplateengines;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Objects;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.sbertech.platformv.print.benchmarktemplateengines.configuration.BenchmarkTemplateEnginesAutoConfiguration;
import ru.sbertech.platformv.print.benchmarktemplateengines.mapper.EmployeeMapperImpl;
import ru.sbertech.platformv.print.benchmarktemplateengines.mapper.OfficeMapperImpl;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.EmployeeRepository;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.OfficeRepository;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.ProjectRepository;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FizzedEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.HttlEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.JinJavaEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.LiqpEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.MustacheEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.PebbleEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.ThymeleafEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.TrimouEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.VelocityEngine;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OfficeMapperImpl.class, FreemarkerEngine.class, ThymeleafEngine.class, FizzedEngine.class
        , MustacheEngine.class,
        TrimouEngine.class,
        VelocityEngine.class,
        PebbleEngine.class,
        LiqpEngine.class,
        HttlEngine.class,
        JinJavaEngine.class,
        EmployeeMapperImpl.class,
        ResourceResolverService.class,
        ProjectRepository.class,
        OfficeRepository.class,
        EmployeeRepository.class, OfficeService.class, ExpectedOutputTest.Configuration.class})
@Testcontainers
@EnableAutoConfiguration(exclude = {BenchmarkTemplateEnginesAutoConfiguration.class})
public abstract class ExpectedOutputTest {


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12.19");

    static {
        postgres.start();
    }

    @TestConfiguration
    static class Configuration {
        @Bean
        public ClassLoader resourceLoader() {
            return Configuration.class.getClassLoader();
        }
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("database.url", postgres::getJdbcUrl);
        registry.add("database.username", postgres::getUsername);
        registry.add("database.password", postgres::getPassword);
        registry.add("database.driver", postgres::getDriverClassName);
    }

    @BeforeClass
    public static void beforeClass() {
        Locale.setDefault(Locale.ENGLISH);
    }

    void assertOutput(final String process, final String output) {
        assertEquals(output.replaceAll("\\s", ""), process.replaceAll("\\s", ""));
    }

    String readExpectedOutputResource(String path) throws IOException {
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
