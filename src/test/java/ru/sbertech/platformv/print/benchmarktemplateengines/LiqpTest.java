package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.LiqpEngine;

public class LiqpTest extends ExpectedOutputTest {
    @Autowired
    private LiqpEngine liqpEngine;

    @Value("${templates.liqp.report}")
    private String report;

    @Value("${templates.liqp.output}")
    private String output;

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        liqpEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),liqpEngine.process());
    }

    @Test
    public void benchmark() throws TemplateException, IOException {
        liqpEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            liqpEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
