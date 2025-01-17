package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines;

import java.io.IOException;
import java.text.ParseException;

import freemarker.template.TemplateException;

public interface ReportEngine {
    void setup(String report) throws IOException, ParseException;
    String process() throws TemplateException, IOException, ParseException;
}
