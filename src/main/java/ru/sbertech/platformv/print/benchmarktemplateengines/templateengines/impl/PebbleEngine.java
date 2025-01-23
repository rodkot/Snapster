package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pebbletemplates.pebble.template.PebbleTemplate;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;


public class PebbleEngine implements ReportEngine {

    private final PebbleTemplate compiledTemplate;
    private final List<CompanyDto> companies;

    private PebbleEngine(String report, List<CompanyDto> companies, Boolean caching) throws IOException {
        this.companies = companies;
        var engine = new io.pebbletemplates.pebble.PebbleEngine.Builder();
        if (caching) {
            engine.cacheActive(true);
        }

        File tempFile = File.createTempFile("pebble", ".html");

        try (FileWriter fileWriter = new FileWriter(tempFile)) {
            fileWriter.write(report);
        }

        compiledTemplate = engine.build().getTemplate(tempFile.getAbsolutePath());
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
