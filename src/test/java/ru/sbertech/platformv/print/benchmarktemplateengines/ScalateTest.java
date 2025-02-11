package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;

public class ScalateTest extends ExpectedOutputTest{


    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() throws IOException, TemplateException {
        ScalateEngine scalateEngine = new ScalateEngine(companies);
        scalateEngine.greet();
    }

}
