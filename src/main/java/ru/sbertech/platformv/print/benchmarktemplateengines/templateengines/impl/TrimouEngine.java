package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trimou.Mustache;
import org.trimou.engine.MustacheEngineBuilder;
import org.trimou.engine.cache.DefaultComputingCacheFactory;
import org.trimou.handlebars.SimpleHelpers;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class TrimouEngine implements ReportEngine {

    private final Mustache mustache;
    private final List<CompanyDto> companies;

    private TrimouEngine(String report, List<CompanyDto> companies, Boolean caching){
        this.companies = companies;
        var engineBuilder =
                MustacheEngineBuilder.newBuilder()
                        .registerHelper("color",
                                new SimpleHelpers.Builder().execute((o, p) -> {
                                    var colors = List.of("#ff5733", "#33ff57", "#3357ff");
                                    o.append(colors.get(((Integer) o.getParameters().get(0)) % colors.size()));
                                }).build());
        if (caching){
            engineBuilder.setComputingCacheFactory(new DefaultComputingCacheFactory());
        }

        mustache = engineBuilder.build().compileMustache("company", report);
    }

    public static TrimouEngine of(String report, List<CompanyDto> companies){
        return new TrimouEngine(report, companies, false);
    }

    public static TrimouEngine cachingOf(String report, List<CompanyDto> companies){
        return new TrimouEngine(report, companies, true);
    }

    private Map<String, Object> setupContext() {
        return Map.of("companies", companies);
    }

    @Override
    public String process() throws TemplateException, IOException {
        return mustache.render(setupContext());
    }
}
