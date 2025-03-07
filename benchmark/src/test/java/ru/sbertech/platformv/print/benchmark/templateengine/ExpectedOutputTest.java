package ru.sbertech.platformv.print.benchmark.templateengine;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.sbertech.platformv.print.benchmark.common.SetupTest;
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

@ContextConfiguration(classes =  ResourceConfig.class)
public abstract class  ExpectedOutputTest  extends SetupTest {

    void assertOutput(final String process, final String output) {
        assertEquals(output.replaceAll("\\s", ""), process.replaceAll("\\s", ""));
    }
}
