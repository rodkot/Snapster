package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.service.CompanyService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.MustacheEngine;

public class MustacheTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportMustache")
    private String report;

    @Autowired
    @Qualifier("outputMustache")
    private String output;

    @Autowired
    private CompanyService companyService;

    @Test
    public void testOutput() {
        var engine = new MustacheEngine(report, companyService.loadAll());
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmark() {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new MustacheEngine(report, companyService.loadAll());
            System.out.println(engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
