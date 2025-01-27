package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.JasperReportEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.XDocReportEngine;

public class JasperReportTest extends ExpectedOutputTest{

    @Autowired
    private List<CompanyDto> companies;

    @Autowired
    @Qualifier("reportJasper")
    private File report;

    @Autowired
    @Qualifier("outputJasper")
    private File output;

    @Test
    public void benchmarkWithOutOptimizations() throws IOException, XDocReportException, TemplateException,
            ParseException, JRException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine = JasperReportEngine.of(new FileInputStream(report), companies);
            engine.process(new FileOutputStream(output, false));
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }



}
