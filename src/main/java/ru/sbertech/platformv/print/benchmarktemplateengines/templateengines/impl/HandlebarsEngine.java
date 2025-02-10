package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.StringReportEngine;

public class HandlebarsEngine implements StringReportEngine {

    private final List<CompanyDto> companies;
    private final Template compiledTemplate;

    private HandlebarsEngine(String report, List<CompanyDto> companies) throws IOException {
        this.companies = companies;
        Handlebars handlebars = new Handlebars();
        compiledTemplate = handlebars.compileInline(report);
    }

    public static HandlebarsEngine of(String report, List<CompanyDto> companies) throws IOException {
        return new HandlebarsEngine(report, companies);
    }

    @Override
    public String process() throws TemplateException, IOException, ParseException, XDocReportException {
        return compiledTemplate.apply(setupContext(companies));
    }

    private Map<String, Object> setupContext(List<CompanyDto> companies){
        return Map.of("companies", companies);
    }
}
