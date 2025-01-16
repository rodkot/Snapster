package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.TemplateFunction;

import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class MustacheEngine implements ReportEngine {
//    @Value("${templates.mustache.path}")
//    private String path;

    @Autowired
    private OfficeService officeService;

    private Mustache mustache;

    @Override
    public void setup(String report) {
        MustacheFactory mf = new DefaultMustacheFactory();
        mustache = mf.compile(new StringReader(report), "office");
    }

    @Override
    public String process() {
        StringWriter writer = new StringWriter();
        mustache.execute(writer, setupContext());

        return writer.toString();
    }

    private Map<String, Object> setupContext() {
        var color = (TemplateFunction) (input ->{
            var colors = List.of("#123456", "#ffffff", "#aaaaaa");
            return colors.get(new Random(100).nextInt(100) % colors.size());
        });

        var offices = officeService.loadAll();

        return Map.of("offices",offices, "getColor", color);
    }
}
