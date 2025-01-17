package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

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

    @Test
    public void benchmark() throws TemplateException, IOException {
        velocityEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            velocityEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
