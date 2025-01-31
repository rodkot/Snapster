package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FizzedEngine;

public class FizzedTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("outputFizzed")
    private String output;

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() throws IOException, TemplateException {
        FizzedEngine engine =  FizzedEngine.of(companies);
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmarkWithOutOptimizations() throws IOException, TemplateException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine =  FizzedEngine.of(companies);
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }

    @Test
    public void benchmarkWithOptimizations() throws IOException, TemplateException {
        Stopwatch sw = Stopwatch.createStarted();
        var engine =  FizzedEngine.of(companies);
        for (int i =0; i< 1000; i++){
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+" ms.");
    }
}
