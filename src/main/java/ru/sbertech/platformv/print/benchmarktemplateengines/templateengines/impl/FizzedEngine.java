package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fizzed.rocker.BindableRockerModel;
import com.fizzed.rocker.Rocker;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class FizzedEngine implements ReportEngine {
    @Autowired
    private OfficeService officeService;

    private BindableRockerModel rockerModel;

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
        var offices = officeService.loadAll();

        return rockerModel.bind("offices", offices).render().toString();
    }
}
