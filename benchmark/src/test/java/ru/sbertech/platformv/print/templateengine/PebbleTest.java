package ru.sbertech.platformv.print.templateengine;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;
import ru.sbertech.platformv.print.templateengine.model.CompanyDto;
import ru.sbertech.platformv.print.templateengine.templateengines.impl.PebbleEngine;

public class PebbleTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportPebble")
    private String report;

    @Autowired
    @Qualifier("outputPebble")
    private String output;

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() throws IOException {
        var engine = PebbleEngine.of(report, companies);
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmarkWithOutOptimizations() throws IOException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine =  PebbleEngine.of(report, companies);
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }

    @Test
    public void benchmarkWithOptimizations() throws IOException {
        Stopwatch sw = Stopwatch.createStarted();
        var engine =  PebbleEngine.cachingOf(report, companies);
        for (int i =0; i< 1000; i++){
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+" ms.");
    }
}