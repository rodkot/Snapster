package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.util.List;
import java.util.Map;

import com.hubspot.jinjava.Jinjava;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class JinJavaEngine implements ReportEngine {

    private final Map<String,Object> context;
    private final String report;
    private final Jinjava jinjava;

    private final List<OfficeDto> offices;

    public JinJavaEngine(String report, List<OfficeDto> offices){
        this.offices = offices;
        this.report = report;
        context = setupContext();
        jinjava = new Jinjava();
    }

    private Map<String,Object> setupContext(){
        return Map.of("offices", offices, "colors", List.of( "#ff5733", "#33ff57" ,"#3357ff"));
    }

    @Override
    public String process() {
        return jinjava.render(report, context);
    }
}
