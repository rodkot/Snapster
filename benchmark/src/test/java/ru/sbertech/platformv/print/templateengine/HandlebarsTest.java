package ru.sbertech.platformv.print.templateengine;

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
import ru.sbertech.platformv.print.templateengine.templateengines.impl.HandlebarsEngine;

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

    @Test
    public void benchmarkWithOutOptimizations() throws IOException, TemplateException, ParseException,
            XDocReportException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            var engine =  HandlebarsEngine.of(report, companies);
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }

    @Test
    public void benchmarkWithOptimizations() throws IOException, TemplateException, ParseException,
            XDocReportException {
        Stopwatch sw = Stopwatch.createStarted();
        var engine =  HandlebarsEngine.cachingOf(report, companies);
        for (int i =0; i< 1000; i++){
            engine.process();
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+" ms.");
    }

}
