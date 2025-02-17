package ru.sbertech.platformv.print.benchmark.templateengine.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.TemplateFunction;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.templateengine.StringReportEngine;


public class MustacheEngine implements StringReportEngine {

    private final Mustache mustache;
    private final List<CompanyDto> companies;

    private MustacheEngine(String
            report, List<CompanyDto> companies){
        this.companies = companies;
        MustacheFactory mf = new DefaultMustacheFactory();
        mustache = mf.compile(new StringReader(report), "company");
    }

    public static MustacheEngine of(String
            report, List<CompanyDto> companies){
        return new MustacheEngine(report, companies);
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

        return Map.of("companies",companies, "getColor", color);
    }
}
