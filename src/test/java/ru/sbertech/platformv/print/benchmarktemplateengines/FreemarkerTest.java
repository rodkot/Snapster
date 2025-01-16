package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;

public class FreemarkerTest extends ExpectedOutputTest {
    @Autowired
    private FreemarkerEngine freemarkerEngine;

    @Value("${templates.freemarker.report}")
    private String report;

    @Value("${templates.freemarker.output}")
    private String output;

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        freemarkerEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),freemarkerEngine.process());
    }
}
