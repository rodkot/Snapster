package ru.sbertech.platformv.print.templateengine;

import com.google.common.base.Stopwatch;
import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import org.eclipse.birt.report.engine.api.EngineException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.sbertech.platformv.print.templateengine.model.CompanyDto;
import ru.sbertech.platformv.print.templateengine.templateengines.impl.BirtEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void benchmarkWithOutOptimizations() throws IOException, XDocReportException, TemplateException,
            ParseException, JRException, EngineException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1; i++){
            var engine = BirtEngine.of(new FileInputStream(report),companies);
            engine.process(new FileOutputStream(output, false));
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }

}