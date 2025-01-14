package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class ThymeleafEngine implements ReportEngine {

    @Value("${templates.thymeleaf.path}")
    private String path;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private ResourceResolverService resourceResolverService;

    private Context context;

    private TemplateEngine templateEngine;

    @Override
    public void setup() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(resourceResolverService.pathDirResource(path));
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

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
        return templateEngine.process( resourceResolverService.nameResource(path), context);
    }
}
