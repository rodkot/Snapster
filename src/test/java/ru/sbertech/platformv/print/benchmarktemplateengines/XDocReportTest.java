package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.XDocReportEngine;

public class XDocReportTest extends ExpectedOutputTest {

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void test() throws IOException, XDocReportException, TemplateException, ParseException {
        var engine = XDocReportEngine.of(XDocReportTest.class.getResourceAsStream("/templates/xdocreport/companies" +
                        ".odt"),
                companies);

        engine.process();
    }


}
