package ru.sbertech.platformv.print.benchmarktemplateengines;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;

public class FreemarkerTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportFreemarker")
    private String report;

    @Autowired
    @Qualifier("outputFreemarker")
    private String output;

    @Autowired
    private OfficeService officeService;


    @Test
    public void testOutput() throws IOException, TemplateException {
        var engine = new FreemarkerEngine(report, officeService.loadAll());
        assertOutput(output,engine.process());
    }

    @Test
    public void benchmark() throws IOException, TemplateException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new FreemarkerEngine(report, officeService.loadAll());
            assertOutput(output,engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
