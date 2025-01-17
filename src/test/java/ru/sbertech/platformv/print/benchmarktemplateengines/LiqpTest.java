package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.LiqpEngine;

public class LiqpTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportLiqp")
    private String report;

    @Autowired
    @Qualifier("outputLiqp")
    private String output;

    @Autowired
    private OfficeService officeService;

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        var engine = new LiqpEngine(report, officeService.loadAll());
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmark() throws TemplateException, IOException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new LiqpEngine(report, officeService.loadAll());
            System.out.println(engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
