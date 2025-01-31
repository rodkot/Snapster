package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.util.List;

import com.fizzed.rocker.BindableRockerModel;
import com.fizzed.rocker.Rocker;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.StringReportEngine;

public class FizzedEngine implements StringReportEngine {

    private final BindableRockerModel rockerModel;
    private final List<CompanyDto> companies;

    public FizzedEngine(String report,List<CompanyDto> companies) throws IOException {
        this.companies = companies;
        rockerModel = Rocker.template(report);
    }

    @Override
    public String process() throws TemplateException, IOException {

        return rockerModel.bind("companies", companies).render().toString();
    }
}
