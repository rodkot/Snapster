package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines;

import java.io.IOException;
import java.text.ParseException;

import freemarker.template.TemplateException;

public interface ReportEngine {
    String process() throws TemplateException, IOException, ParseException;
}
