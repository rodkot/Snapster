package ru.sbertech.platformv.print.benchmark.templateengine.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.pebbletemplates.pebble.loader.StringLoader;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.templateengine.StringReportEngine;


public class PebbleEngine implements StringReportEngine {

    private final PebbleTemplate compiledTemplate;
    private final List<CompanyDto> companies;

    private PebbleEngine(String report, List<CompanyDto> companies, Boolean caching) {
        this.companies = companies;
        var engine = new io.pebbletemplates.pebble.PebbleEngine.Builder().loader(new StringLoader());
        if (caching) {
            engine.cacheActive(true);
        }
        compiledTemplate = engine.build().getTemplate(report);
    }

    public static PebbleEngine of(String report, List<CompanyDto> companies) throws IOException {
        return new PebbleEngine(report, companies, false);
    }

    public static PebbleEngine cachingOf(String report, List<CompanyDto> companies) throws IOException {
        return new PebbleEngine(report, companies, true);
    }

    @Override
    public String process() throws IOException {
        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, setupContext());

        return writer.toString();
    }

    private Map<String, Object> setupContext() {
        var context = new HashMap<String, Object>();

        context.put("companies", companies);

        return context;
    }
}
