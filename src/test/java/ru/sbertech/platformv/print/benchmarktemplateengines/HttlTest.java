package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.HttlEngine;

public class HttlTest extends ExpectedOutputTest {
    @Autowired
    private HttlEngine httlEngine;

    @Value("${templates.httl.report}")
    private String report;

    @Value("${templates.httl.output}")
    private String output;

    @Test
    public void testOutput() throws IOException, ParseException {
        httlEngine.setup(readExpectedOutputResource(report));
        assertOutput(readExpectedOutputResource(output),httlEngine.process());
    }
}
