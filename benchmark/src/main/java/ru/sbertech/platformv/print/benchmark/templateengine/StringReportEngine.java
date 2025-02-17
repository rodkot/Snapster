package ru.sbertech.platformv.print.benchmark.templateengine;

import java.io.IOException;
import java.text.ParseException;

import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;

public interface StringReportEngine {
    String process() throws TemplateException, IOException, ParseException, XDocReportException;
}
