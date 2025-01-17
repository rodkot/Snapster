package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Stopwatch;

import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.PebbleEngine;

public class PebbleTest extends ExpectedOutputTest {

    private PebbleEngine pebbleEngine;

    @Value("${templates.pebble.report}")
    private String report;

    @Value("${templates.pebble.output}")
    private String output;

    @Test
    public void testFreemarkerOutput() throws IOException {
        pebbleEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),pebbleEngine.process());
    }

    @Test
    public void benchmark() throws IOException {
        pebbleEngine.setup(readExpectedOutputResource(report));
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 100; i++){
            pebbleEngine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
    }
}