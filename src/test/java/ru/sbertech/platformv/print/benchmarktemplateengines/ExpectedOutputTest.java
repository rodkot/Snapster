package ru.sbertech.platformv.print.benchmarktemplateengines;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Objects;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.configuration.BenchmarkTemplateEnginesAutoConfiguration;
import ru.sbertech.platformv.print.benchmarktemplateengines.mapper.EmployeeMapperImpl;
import ru.sbertech.platformv.print.benchmarktemplateengines.mapper.OfficeMapperImpl;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.EmployeeRepository;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.OfficeRepository;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.ProjectRepository;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FreemarkerEngine.class, OfficeMapperImpl.class, EmployeeMapperImpl.class,
        ProjectRepository.class,
        OfficeRepository.class,
        EmployeeRepository.class, OfficeService.class})
@Testcontainers
@EnableAutoConfiguration(exclude = {BenchmarkTemplateEnginesAutoConfiguration.class})
public class ExpectedOutputTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12.19");

    static {
        postgres.start();
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

    @Autowired
    private FreemarkerEngine freemarkerEngine;

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        freemarkerEngine.setup();
        assertOutput(freemarkerEngine.process());
    }

    private void assertOutput(final String output) throws IOException {
        assertEquals(readExpectedOutputResource(), output.replaceAll("\\s", ""));
    }

    private String readExpectedOutputResource() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ExpectedOutputTest.class.getResourceAsStream("/output.html"))))) {
            for (; ; ) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line);
            }
        }

        return builder.toString().replaceAll("\\s", "");
    }
}
