package ru.sbertech.platformv.print.benchmark.templateengine;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.templateengine.impl.MustacheEngine;

public class MustacheTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportMustache")
    private String report;

    @Autowired
    @Qualifier("outputMustache")
    private String output;


    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() {
        var engine = MustacheEngine.of(report, companies);
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmarkWithOutOptimizations() {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine =  MustacheEngine.of(report, companies);
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }

}
