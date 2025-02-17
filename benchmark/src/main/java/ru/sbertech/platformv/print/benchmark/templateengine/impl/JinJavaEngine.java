package ru.sbertech.platformv.print.benchmark.templateengine.impl;

import java.util.List;
import java.util.Map;

import com.hubspot.jinjava.Jinjava;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.templateengine.StringReportEngine;


public class JinJavaEngine implements StringReportEngine {

    private final Map<String, Object> context;
    private final String report;
    private final Jinjava jinjava;

    private final List<CompanyDto> companies;

    private JinJavaEngine(String report, List<CompanyDto> companies) {
        this.companies = companies;
        this.report = report;
        context = setupContext();
        jinjava = new Jinjava();
    }

    public static JinJavaEngine of(String report, List<CompanyDto> companies) {
        return new JinJavaEngine(report, companies);
    }

    private Map<String, Object> setupContext() {
        return Map.of("companies", companies, "colors", List.of("#ff5733", "#33ff57", "#3357ff"));
    }

    @Override
    public String process() {
        return jinjava.render(report, context);
    }
}
