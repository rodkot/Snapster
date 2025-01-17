package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.HttlEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.JinJavaEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.PebbleEngine;

public class PebbleTest extends ExpectedOutputTest {

    @Autowired
    private OfficeService officeService;

    @Autowired
    @Qualifier("reportPebble")
    private String report;

    @Autowired
    @Qualifier("outputPebble")
    private String output;

    @Test
    public void testOutput() throws IOException {
        var engine = new PebbleEngine(report, officeService.loadAll());
        assertOutput(output, engine.process());
    }

    @Test
    public void benchmark() throws IOException, ParseException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new HttlEngine(report, officeService.loadAll());
            System.out.println(engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}