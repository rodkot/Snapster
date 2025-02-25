package ru.sbertech.platformv.print.benchmark.common;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.sbertech.platformv.print.benchmark.domain.configuration.BenchmarkTemplateEnginesAutoConfiguration;
import ru.sbertech.platformv.print.benchmark.domain.mapper.CompanyMapperImpl;
import ru.sbertech.platformv.print.benchmark.domain.mapper.EmployeeMapperImpl;
import ru.sbertech.platformv.print.benchmark.domain.mapper.OfficeMapperImpl;
import ru.sbertech.platformv.print.benchmark.domain.repository.CompanyRepository;
import ru.sbertech.platformv.print.benchmark.domain.repository.EmployeeRepository;
import ru.sbertech.platformv.print.benchmark.domain.repository.OfficeRepository;
import ru.sbertech.platformv.print.benchmark.domain.repository.ProjectRepository;
import ru.sbertech.platformv.print.benchmark.domain.service.CompanyService;
import ru.sbertech.platformv.print.benchmark.domain.service.OfficeService;
import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;
import ru.sbertech.platformv.print.benchmark.templateengine.ResourceConfig;

import java.util.Locale;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = "ru.sbertech.platformv.print.benchmark.domain.repository")
@EntityScan(basePackages = "ru.sbertech.platformv.print.benchmark.domain.model.entity")
@SpringBootTest(classes = {OfficeMapperImpl.class,
        EmployeeMapperImpl.class,
        ResourceResolverService.class,
        ProjectRepository.class,
        OfficeRepository.class,
        CompanyRepository.class,
        CompanyMapperImpl.class,
        CompanyService.class,
        EmployeeRepository.class, OfficeService.class})
@Testcontainers
@EnableAutoConfiguration(exclude = {BenchmarkTemplateEnginesAutoConfiguration.class})
public abstract class SetupTest {
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
}
