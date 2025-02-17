package ru.sbertech.platformv.print.templateengine;

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
import ru.sbertech.platformv.print.templateengine.model.CompanyDto;
import ru.sbertech.platformv.print.templateengine.templateengines.impl.XDocReportEngine;

public class XDocReportTest extends ExpectedOutputTest {

    @Autowired
    private List<CompanyDto> companies;

    @Autowired
    @Qualifier("reportXDoc")
    private File report;

    @Autowired
    @Qualifier("outputXDoc")
    private File output;

    @Test
    public void benchmarkWithOutOptimizations() throws IOException, XDocReportException, TemplateException, ParseException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine = XDocReportEngine.of(new FileInputStream(report), companies);
            engine.process(new FileOutputStream(output, false));
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }
}
