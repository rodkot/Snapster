package ru.sbertech.platformv.print.templateengine;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.sbertech.platformv.print.templateengine.configuration.BenchmarkTemplateEnginesAutoConfiguration;
import ru.sbertech.platformv.print.templateengine.mapper.CompanyMapperImpl;
import ru.sbertech.platformv.print.templateengine.mapper.EmployeeMapperImpl;
import ru.sbertech.platformv.print.templateengine.mapper.OfficeMapperImpl;
import ru.sbertech.platformv.print.templateengine.repository.*;
import ru.sbertech.platformv.print.templateengine.service.CompanyService;
import ru.sbertech.platformv.print.templateengine.service.OfficeService;
import ru.sbertech.platformv.print.templateengine.service.ResourceResolverService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OfficeMapperImpl.class,
        EmployeeMapperImpl.class,
        ResourceResolverService.class,
        ProjectRepository.class,
        OfficeRepository.class,
        CompanyRepository.class,
        CompanyMapperImpl.class,
        CompanyService.class,
        EmployeeRepository.class, OfficeService.class, ResourceConfig.class})
@Testcontainers
@EnableAutoConfiguration(exclude = {BenchmarkTemplateEnginesAutoConfiguration.class})
public abstract class   ExpectedOutputTest {

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

    void assertOutput(final String process, final String output) {
        assertEquals(output.replaceAll("\\s", ""), process.replaceAll("\\s", ""));
    }
}
