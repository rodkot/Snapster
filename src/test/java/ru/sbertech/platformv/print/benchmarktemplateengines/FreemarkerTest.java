package ru.sbertech.platformv.print.benchmarktemplateengines;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

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
    public void testOutput() throws IOException, TemplateException {
        freemarkerEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),freemarkerEngine.process());
    }

    @Test
    public void benchmark() throws IOException, TemplateException {
        freemarkerEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            freemarkerEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
