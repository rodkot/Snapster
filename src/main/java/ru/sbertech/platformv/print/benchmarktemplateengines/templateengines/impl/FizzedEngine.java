package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fizzed.rocker.BindableRockerModel;
import com.fizzed.rocker.Rocker;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class FizzedEngine implements ReportEngine {

    private BindableRockerModel rockerModel;
    private final List<OfficeDto> offices;

    public FizzedEngine(List<OfficeDto> offices){
        this.offices = offices;
    }

    @Override
    public void setup(String report) throws IOException {
//        File templateFile = File.createTempFile("template",".rocker.html");
//
//        try (FileWriter fileWriter = new FileWriter(templateFile)){
//            fileWriter.write(report);
//        }

        rockerModel = Rocker.template("index.rocker.html");
    }

    @Override
    public String process() throws TemplateException, IOException {

        return rockerModel.bind("offices", offices).render().toString();
    }
}
