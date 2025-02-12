package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ScalateEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.ThymeleafEngine;

public class ScalateTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportScalateSsp")
    private String report;

    @Autowired
    @Qualifier("outputScalate")
    private String output;

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutputSPP() {
        ScalateEngine engine = ScalateEngine.ofSPP(report, companies);
        assertOutput(engine.process(), output);
    }

    @Test
    public void benchmarkWithOutOptimizationsSSP() {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine =  ScalateEngine.ofSPP(report, companies);
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }

    @Test
    public void benchmarkWithOptimizationsSSP() {
        Stopwatch sw = Stopwatch.createStarted();
        var engine =  ScalateEngine.ofCashingSPP(report, companies);
        for (int i =0; i< 1000; i++){
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+" ms.");
    }
}
