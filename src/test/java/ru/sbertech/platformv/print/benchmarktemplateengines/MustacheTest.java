package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

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

    @Test
    public void benchmark() throws IOException {
        mustacheEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            mustacheEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
