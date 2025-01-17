package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.HttlEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.JinJavaEngine;

public class JinJavaTest extends ExpectedOutputTest {

    private JinJavaEngine jinJavaEngine;

    @Value("${templates.jinjava.report}")
    private String report;

    @Value("${templates.jinjava.output}")
    private String output;

    @Autowired
    private OfficeService officeService;

    @BeforeEach
    public void setup(){
        jinJavaEngine = new JinJavaEngine(officeService.loadAll());
    }

    @Test
    public void testOutput() throws IOException {
        jinJavaEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),jinJavaEngine.process());
    }

    @Test
    public void benchmark() throws IOException {
        jinJavaEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            jinJavaEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}

