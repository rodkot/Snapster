package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.util.List;

import com.fizzed.rocker.BindableRockerModel;
import com.fizzed.rocker.Rocker;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class FizzedEngine implements ReportEngine {

    private final BindableRockerModel rockerModel;
    private final List<OfficeDto> offices;

    public FizzedEngine(List<OfficeDto> offices) {
        this.offices = offices;
        rockerModel = Rocker.template("index.rocker.html");
    }

    @Override
    public String process() throws TemplateException, IOException {

        return rockerModel.bind("offices", offices).render().toString();
    }
}
