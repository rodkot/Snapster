package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.JinJavaEngine;

public class JinJavaTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportJinjava")
    private String report;

    @Autowired
    @Qualifier("outputJinjava")
    private String output;

    @Autowired
    private OfficeService officeService;

    @Test
    public void testOutput() {
        var engine = new JinJavaEngine(report, officeService.loadAll());
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmark() {

        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new JinJavaEngine(report, officeService.loadAll());
            System.out.println(engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}

