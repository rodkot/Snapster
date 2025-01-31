package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
}
