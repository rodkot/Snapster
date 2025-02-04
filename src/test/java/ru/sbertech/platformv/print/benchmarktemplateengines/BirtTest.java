package ru.sbertech.platformv.print.benchmarktemplateengines;

import freemarker.template.TemplateException;
import org.eclipse.birt.report.engine.api.EngineException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.BirtEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.FreemarkerEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BirtTest extends ExpectedOutputTest {

    @Autowired
    private List<CompanyDto> companies;

    @Autowired
    @Qualifier("reportBirt")
    private File report;

    @Autowired
    @Qualifier("outputBirt")
    private File output;

    @Test
    public void testOutput() throws IOException, TemplateException, EngineException {
        var engine = BirtEngine.of(new FileInputStream(report), companies);
        engine.process(new FileOutputStream(output));
    }

}