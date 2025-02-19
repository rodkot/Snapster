package ru.sbertech.platformv.print.benchmark.templateengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Stopwatch;

import clojure.lang.IFn;
import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import ru.sbertech.platformv.print.benchmark.templateengine.impl.DocxStamperEngine;

public class StencilTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("companiesMap")
    private Map<String,Object> companies;

    @Autowired
    @Qualifier("reportStencil")
    private File report;

    @Autowired
    @Qualifier("outputStencil")
    private File output;

    @Autowired
    @Qualifier("stencilEngine")
    private IFn engine;

    @Test
    public void testOutput(){
        String templatePath = report.getAbsolutePath();
        String outputPath = output.getAbsolutePath();

        engine.invoke(templatePath, outputPath, companies);
    }

    @Test
    public void benchmarkWithOutOptimizations() throws IOException, XDocReportException, TemplateException,
            ParseException, JRException {
        Stopwatch sw = Stopwatch.createStarted();
        for (int i =0; i< 1000; i++){
            engine.invoke(report.getAbsolutePath(), output.getAbsolutePath(), companies);
        }
        System.out.println(sw.elapsed(TimeUnit.MILLISECONDS)+ " ms.");
    }
}
