package ru.sbertech.platformv.print.benchmark.templateengine;

import freemarker.template.TemplateException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.kotlin.templateengine.BarberEngine;

import java.io.IOException;
import java.util.List;

public class BarberTest extends ExpectedOutputTest{
    @Autowired
    @Qualifier("reportBarber")
    private String report;

    @Autowired
    @Qualifier("outputBarber")
    private String output;

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() throws IOException, TemplateException {
        var engine = BarberEngine.Companion.of(report, companies);
        assertOutput(output,engine.process());
    }
}
