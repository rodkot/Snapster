package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubspot.jinjava.Jinjava;

import httl.Engine;
import httl.Template;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class HttlEngine implements ReportEngine {

    @Autowired
    private OfficeService officeService;

    private Map<String, Object> context;
    private Template template;

    @Override
    public void setup(String report) throws ParseException {
        Engine engine = Engine.getEngine();

        template = engine.parseTemplate(report);
        context = setupContext();
    }

    private Map<String,Object> setupContext(){

        var offices = officeService.loadAll();

        return Map.of("offices", offices, "colors", List.of( "#ff5733", "#33ff57" ,"#3357ff"));
    }

    @Override
    public String process() throws IOException, ParseException {
        StringWriter stringWriter = new StringWriter();
        template.render(context, stringWriter);

        return stringWriter.toString();
    }
}
