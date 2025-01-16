package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.PebbleEngine;

public class PebbleTest extends ExpectedOutputTest {
    @Autowired
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
}