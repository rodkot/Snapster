package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.util.List;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.StringReportEngine;

public class FizzedEngine implements StringReportEngine {

    private final List<CompanyDto> companies;

    private FizzedEngine(List<CompanyDto> companies) {
        this.companies = companies;
    }

    public static FizzedEngine of(List<CompanyDto> companies){
        return new FizzedEngine(companies);
    }

    @Override
    public String process() throws TemplateException, IOException {
        return templates.fizzed.companies.template(companies).render().toString();
    }
}
