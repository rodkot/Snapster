package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.HttlEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.JinJavaEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.LiqpEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.MustacheEngine;

public class MustacheTest extends ExpectedOutputTest {

    @Value("${templates.mustache.report}")
    private String report;

    @Value("${templates.mustache.output}")
    private String output;

    @Autowired
    private OfficeService officeService;

    @Test
    public void testFreemarkerOutput() throws IOException {
        var engine = new MustacheEngine(readExpectedOutputResource(report), officeService.loadAll());
        assertOutput(readExpectedOutputResource(output),engine.process());
    }

    @Test
    public void benchmark() throws IOException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            var engine = new MustacheEngine(readExpectedOutputResource(report), officeService.loadAll());
            System.out.println(engine.process());
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
