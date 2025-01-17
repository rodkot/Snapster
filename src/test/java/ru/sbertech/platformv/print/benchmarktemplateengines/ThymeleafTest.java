package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.ThymeleafEngine;

public class ThymeleafTest extends ExpectedOutputTest {
    @Autowired
    private ThymeleafEngine thymeleafEngine;

    @Value("${templates.thymeleaf.report}")
    private String report;

    @Value("${templates.thymeleaf.output}")
    private String output;

    @Test
    public void testOutput() throws IOException {
        thymeleafEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),thymeleafEngine.process());
    }

    @Test
    public void benchmark() throws IOException {
        thymeleafEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            thymeleafEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
