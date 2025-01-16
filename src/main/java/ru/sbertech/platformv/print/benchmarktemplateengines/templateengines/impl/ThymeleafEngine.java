package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class ThymeleafEngine implements ReportEngine {

    private String report;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private ResourceResolverService resourceResolverService;

    private Context context;

    private TemplateEngine templateEngine;

    @Override
    public void setup(String report) {
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

        var offices = officeService.loadAll();

        context.setVariable("offices", offices);

        return context;
    }

    @Override
    public String process() {
        return templateEngine.process( report, context);
    }
}
