package ru.sbertech.platformv.print.benchmark.templateengine.impl;

import java.util.List;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.templateengine.StringReportEngine;

public class ThymeleafEngine implements StringReportEngine {

    private final String report;
    private final Context context;
    private final TemplateEngine templateEngine;
    private final List<CompanyDto> companies;

    private ThymeleafEngine(String report, List<CompanyDto> companies, Boolean caching) {
        this.companies = companies;
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode("HTML");
        stringTemplateResolver.setCacheable(caching);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(stringTemplateResolver);
        this.report = report;

        context = setupContext();
    }

    public static ThymeleafEngine of(String report, List<CompanyDto> companies) {
        return new ThymeleafEngine(report, companies, false);
    }

    public static ThymeleafEngine cachingOf(String report, List<CompanyDto> companies) {
        return new ThymeleafEngine(report, companies, true);
    }

    private Context setupContext() {
        var context = new Context();

        context.setVariable("companies", companies);

        return context;
    }

    @Override
    public String process() {
        return templateEngine.process(report, context);
    }
}
