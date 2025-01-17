package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.MustacheEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.ThymeleafEngine;

public class ThymeleafTest extends ExpectedOutputTest {

    private ThymeleafEngine thymeleafEngine;

    @Value("${templates.thymeleaf.report}")
    private String report;

    @Value("${templates.thymeleaf.output}")
    private String output;

    @Autowired
    private OfficeService officeService;

    @BeforeEach
    public void setup(){
        thymeleafEngine = new ThymeleafEngine(officeService.loadAll());
    }

    @Test
    public void testOutput() throws IOException {
        thymeleafEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),thymeleafEngine.process());
    }

    @Test
    public void benchmark() throws IOException {
        thymeleafEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            thymeleafEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}
