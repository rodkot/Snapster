package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

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

    @Test
    public void benchmark() throws TemplateException, IOException {
        trimouEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            trimouEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}