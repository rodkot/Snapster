package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.VelocityEngine;

public class VelocityTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportVelocity")
    private String report;

    @Autowired
    @Qualifier("outputVelocity")
    private String output;
    @Autowired
    private OfficeService officeService;

    @Test
    public void testOutput() throws IOException, TemplateException {
        var engine = new VelocityEngine(report, officeService.loadAll());
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmark() throws TemplateException, IOException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new VelocityEngine(report, officeService.loadAll());
            System.out.println(engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
