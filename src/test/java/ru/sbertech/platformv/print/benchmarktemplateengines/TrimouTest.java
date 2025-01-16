package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.TrimouEngine;

public class TrimouTest extends ExpectedOutputTest {
    @Autowired
    private TrimouEngine trimouEngine;

    @Value("${templates.trimou.report}")
    private String report;

    @Value("${templates.trimou.output}")
    private String output;

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        trimouEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),trimouEngine.process());
    }
}