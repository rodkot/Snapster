package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class ThymeleafEngine implements ReportEngine {

    private final String report;
    private final Context context;
    private final TemplateEngine templateEngine;
    private final List<CompanyDto> companies;

    public ThymeleafEngine(String report, List<CompanyDto> companies){
        this.companies = companies;
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode("HTML");
        stringTemplateResolver.setCacheable(false);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(stringTemplateResolver);
        this.report = report;

        context = setupContext();
    }

    private Context setupContext(){
        var context = new Context();

        context.setVariable("companies", companies);

        return context;
    }

    @Override
    public String process() {
        return templateEngine.process( report, context);
    }
}
