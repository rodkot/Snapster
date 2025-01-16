package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.MustacheEngine;

public class MustacheTest extends ExpectedOutputTest {
    @Autowired
    private MustacheEngine mustacheEngine;

    @Value("${templates.mustache.report}")
    private String report;

    @Value("${templates.mustache.output}")
    private String output;

    @Test
    public void testFreemarkerOutput() throws IOException {
        mustacheEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),mustacheEngine.process());
    }
}
