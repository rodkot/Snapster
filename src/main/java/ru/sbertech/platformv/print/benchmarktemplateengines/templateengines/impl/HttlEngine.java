package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import httl.Engine;
import httl.Template;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class HttlEngine implements ReportEngine {

    private final Map<String, Object> context;
    private final List<CompanyDto> companies;
    private final Template template;

    public HttlEngine(String report, List<CompanyDto> companies) throws ParseException {
        this.companies = companies;
        Engine engine = Engine.getEngine();

        template = engine.parseTemplate(report);
        context = setupContext();
    }

    private Map<String,Object> setupContext(){

        return Map.of("companies", companies, "colors", List.of( "#ff5733", "#33ff57" ,"#3357ff"));
    }

    @Override
    public String process() throws IOException, ParseException {
        StringWriter stringWriter = new StringWriter();
        template.render(context, stringWriter);

        return stringWriter.toString();
    }
}
