package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.VelocityEngine;

public class VelocityTest extends ExpectedOutputTest {
    @Autowired
    private VelocityEngine velocityEngine;

    @Value("${templates.velocity.report}")
    private String report;

    @Value("${templates.velocity.output}")
    private String output;

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        velocityEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),velocityEngine.process());
    }
}
