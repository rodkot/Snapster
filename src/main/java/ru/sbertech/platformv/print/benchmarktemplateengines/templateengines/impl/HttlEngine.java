package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import httl.Engine;
import httl.Template;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class HttlEngine implements ReportEngine {

    private final Map<String, Object> context;
    private final List<CompanyDto> companies;
    private final Template template;

    private HttlEngine(String report, List<CompanyDto> companies, Boolean caching) throws ParseException {
        this.companies = companies;
        Properties properties = new Properties();
        if (caching){
            properties.setProperty("cache.capacity", "1000");
        }

        Engine engine = Engine.getEngine(properties);

        template = engine.parseTemplate(report);
        context = setupContext();
    }

    public static HttlEngine of(String report, List<CompanyDto> companies) throws ParseException {
        return new HttlEngine(report, companies, false);
    }

    public static HttlEngine cachingOf(String report, List<CompanyDto> companies) throws ParseException {
        return new HttlEngine(report, companies, false);
    }

    private Map<String, Object> setupContext() {

        return Map.of("companies", companies, "colors", List.of("#ff5733", "#33ff57", "#3357ff"));
    }

    @Override
    public String process() throws IOException, ParseException {
        StringWriter stringWriter = new StringWriter();
        template.render(context, stringWriter);

        return stringWriter.toString();
    }
}
