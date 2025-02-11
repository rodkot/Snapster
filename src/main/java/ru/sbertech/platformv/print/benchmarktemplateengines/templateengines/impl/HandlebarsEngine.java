package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.cache.ConcurrentMapTemplateCache;

import fr.opensagres.xdocreport.core.XDocReportException;
import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.StringReportEngine;

public class HandlebarsEngine implements StringReportEngine {

    private final List<CompanyDto> companies;
    private final Template compiledTemplate;

    private HandlebarsEngine(String report, List<CompanyDto> companies, Boolean cashing) throws IOException {
        this.companies = companies;
        Handlebars handlebars;
        if (cashing){
            handlebars = new Handlebars().with(new ConcurrentMapTemplateCache());
        }else {
            handlebars = new Handlebars();
        }

        compiledTemplate = handlebars.compileInline(report);
    }

    public static HandlebarsEngine of(String report, List<CompanyDto> companies) throws IOException {
        return new HandlebarsEngine(report, companies, false);
    }

    public static HandlebarsEngine cachingOf(String report, List<CompanyDto> companies) throws IOException {
        return new HandlebarsEngine(report, companies, true);
    }

    @Override
    public String process() throws TemplateException, IOException, ParseException, XDocReportException {
        return compiledTemplate.apply(setupContext(companies));
    }

    private Map<String, Object> setupContext(List<CompanyDto> companies){
        var colors =
                List.of("#ff5733", "#33ff57", "#3357ff", "#ff5733");
        return Map.of("companies", companies, "resourcesColors", colors);
    }
}
