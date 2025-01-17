package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FizzedEngine;

public class FizzedTest extends ExpectedOutputTest {

    @Value("${templates.fizzed.report}")
    private String report;

    @Value("${templates.fizzed.output}")
    private String output;

    @Autowired
    private OfficeService officeService;

    @Test
    public void testOutput() throws IOException, TemplateException {
        FizzedEngine fizzedEngine = new FizzedEngine(officeService.loadAll());
        assertOutput(readExpectedOutputResource(output),fizzedEngine.process());
    }
}
