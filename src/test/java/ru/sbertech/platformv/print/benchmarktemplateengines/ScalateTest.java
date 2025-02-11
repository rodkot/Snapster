package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;

public class ScalateTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("reportScalateSsp")
    private String report;

    @Autowired
    @Qualifier("outputScalate")
    private String output;

    @Autowired
    private List<CompanyDto> companies;

    @Test
    public void testOutput() throws IOException, TemplateException {
        ScalateSspEngine scalateSspEngine = new ScalateSspEngine(report, companies);
        assertOutput(scalateSspEngine.process(), output);
    }

}
