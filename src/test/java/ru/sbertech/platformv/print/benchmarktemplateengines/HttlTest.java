package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.HttlEngine;

public class HttlTest extends ExpectedOutputTest {
    @Autowired
    private HttlEngine httlEngine;

    @Value("${templates.httl.report}")
    private String report;

    @Value("${templates.httl.output}")
    private String output;

    @Test
    public void testOutput() throws IOException, ParseException {
        httlEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),httlEngine.process());
    }

    @Test
    public void benchmark() throws IOException, ParseException {
        httlEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            httlEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }

}
