package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.ThymeleafEngine;

public class ThymeleafTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportThymeleaf")
    private String report;

    @Autowired
    @Qualifier("outputThymeleaf")
    private String output;
    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() {
        var engine = ThymeleafEngine.of(report, companies);
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmarkWithOutOptimizations() {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine =  ThymeleafEngine.of(report, companies);
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }

    @Test
    public void benchmarkWithOptimizations() {
        Stopwatch sw = Stopwatch.createStarted();
        var engine =  ThymeleafEngine.cachingOf(report, companies);
        for (int i =0; i< 1000; i++){
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+" ms.");
    }
}
