package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FizzedEngine;

public class FizzedTest extends ExpectedOutputTest {
    @Autowired
    private FizzedEngine fizzedEngine;

    @Value("${templates.fizzed.report}")
    private String report;

    @Value("${templates.fizzed.output}")
    private String output;

    @Test
    public void testOutput() throws IOException, TemplateException {
        fizzedEngine.setup("");
        assertOutput(readExpectedOutputResource(output),fizzedEngine.process());
    }
}
