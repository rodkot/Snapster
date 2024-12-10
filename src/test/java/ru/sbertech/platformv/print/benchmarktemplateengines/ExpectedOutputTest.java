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
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import freemarker.template.TemplateException;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@EnableTransactionManagement
@EntityScan(basePackages = "ru.sbertech.platformv.print.benchmarktemplateengines.model")
@EnableJpaRepositories(basePackages = "ru.sbertech.platformv.print.benchmarktemplateengines.repository")
@SpringBootTest(classes = {Freemarker.class, ProjectRepository.class})
public class ExpectedOutputTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12.19");

    @BeforeClass
    public static void beforeClass() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Autowired
    private Freemarker freemarker;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        var result = projectRepository.findAll();
        freemarker.setup();
        assertOutput(freemarker.benchmark());
    }

    private void assertOutput(final String output) throws IOException {
        assertEquals(readExpectedOutputResource(), output.replaceAll("\\s", ""));
    }

    private String readExpectedOutputResource() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ExpectedOutputTest.class.getResourceAsStream("/output.html"))))) {
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line);
            }
        }
        // Remove all whitespaces
        return builder.toString().replaceAll("\\s", "");
    }
}
