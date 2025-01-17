package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.JinJavaEngine;

public class JinJavaTest extends ExpectedOutputTest {
    @Autowired
    private JinJavaEngine jinJavaEngine;

    @Value("${templates.jinjava.report}")
    private String report;

    @Value("${templates.jinjava.output}")
    private String output;

    @Test
    public void testOutput() throws IOException {
        jinJavaEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),jinJavaEngine.process());
    }
}

