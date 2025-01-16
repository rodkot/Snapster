package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.ThymeleafEngine;

public class ThymeleafTest extends ExpectedOutputTest {
    @Autowired
    private ThymeleafEngine thymeleafEngine;

    @Value("${templates.thymeleaf.report}")
    private String report;

    @Value("${templates.thymeleaf.output}")
    private String output;

    @Test
    public void testOutput() throws IOException {
        thymeleafEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),thymeleafEngine.process());
    }
}
