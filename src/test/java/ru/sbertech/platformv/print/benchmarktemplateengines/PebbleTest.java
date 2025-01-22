package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.service.CompanyService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.PebbleEngine;

public class PebbleTest extends ExpectedOutputTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    @Qualifier("reportPebble")
    private String report;

    @Autowired
    @Qualifier("outputPebble")
    private String output;

    @Test
    public void testOutput() throws IOException {
        var engine = new PebbleEngine(report, companyService.loadAll());
        assertOutput(output, engine.process());
    }

    @Test
    public void benchmark() throws IOException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new PebbleEngine(report, companyService.loadAll());
            System.out.println(engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}