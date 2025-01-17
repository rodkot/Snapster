package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import com.hubspot.jinjava.Jinjava;

import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class JinJavaEngine implements ReportEngine {

    @Autowired
    private OfficeService officeService;

    private Map<String,Object> context;
    private String report;
    private Jinjava jinjava;

    @Override
    public void setup(String report) {
     this.report = report;
     context = setupContext();
     jinjava = new Jinjava();
    }

    private Map<String,Object> setupContext(){

        var offices = officeService.loadAll();

        return Map.of("offices", offices, "colors", List.of( "#ff5733", "#33ff57" ,"#3357ff"));
    }

    @Override
    public String process() {
        return jinjava.render(report, context);
    }
}
