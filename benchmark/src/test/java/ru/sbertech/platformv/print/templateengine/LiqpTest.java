package ru.sbertech.platformv.print.templateengine;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.templateengine.model.CompanyDto;
import ru.sbertech.platformv.print.templateengine.templateengines.impl.LiqpEngine;

public class LiqpTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportLiqp")
    private String report;

    @Autowired
    @Qualifier("outputLiqp")
    private String output;

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() throws IOException, TemplateException {
        var engine = LiqpEngine.of(report, companies);
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmarkWithOutOptimizations() throws IOException, TemplateException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine =  LiqpEngine.of(report, companies);
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }
}
