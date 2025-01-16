package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.trimou.Mustache;
import org.trimou.engine.MustacheEngineBuilder;
import org.trimou.handlebars.SimpleHelpers;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class TrimouEngine implements ReportEngine {
    @Autowired
    private OfficeService officeService;

    private Mustache mustache;

    @Override
    public void setup(String report) throws IOException {
        var engine =
                MustacheEngineBuilder.newBuilder()
                        .registerHelper("color",
                                new SimpleHelpers.Builder().execute((o, p) -> {
                                    var colors = List.of("#ff5733", "#33ff57", "#3357ff");
                                    o.append(colors.get(((Integer) o.getParameters().get(0)) % colors.size()));
                                }).build()).
                        build();

        mustache = engine.compileMustache("offices", report);
    }

    private Map<String, Object> setupContext() {
        var offices = officeService.loadAll();

        return Map.of("offices", offices);
    }

    @Override
    public String process() throws TemplateException, IOException {
        return mustache.render(setupContext());
    }
}
