package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.CompanyService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;

public class FreemarkerTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportFreemarker")
    private String report;

    @Autowired
    @Qualifier("outputFreemarker")
    private String output;

    @Autowired
    private CompanyService companyService;


    @Test
    public void testOutput() throws IOException, TemplateException {
        var engine = new FreemarkerEngine(report, companyService.loadAll());
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmark() throws IOException, TemplateException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new FreemarkerEngine(report, companyService.loadAll());
            assertOutput(output,engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
