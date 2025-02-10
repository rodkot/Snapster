package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.HandlebarsEngine;

public class HandlebarsTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportHandlebars")
    private String report;

    @Autowired
    @Qualifier("outputHandlebars")
    private String output;

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() throws IOException, TemplateException, ParseException, XDocReportException {
        var engine = HandlebarsEngine.of(report, companies);
        assertOutput(output,engine.process());
    }
}
