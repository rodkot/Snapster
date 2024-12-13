package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.Project;


@RequiredArgsConstructor
public class FreemarkerEngine implements ReportEngine<Project, String> {

    @Value("${templates.freemarker.path}")
    private String path;

    private Map<String, Object> context;

    private Template template;

    @Override
    public void setup(List<Project> values) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), "/"));
        template = configuration.getTemplate(path);
        //this.context = getContext();
    }

    @Override
    public String process() throws TemplateException, IOException {
        Writer writer = new StringWriter();
        template.process(context, writer);
        return writer.toString();
    }

//    @Setup
//    public void setup() throws IOException {
//        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
//        configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), "/"));
//        template = configuration.getTemplate(path);
//        this.context = getContext();
//    }
//
//    @Benchmark
//    public String benchmark() throws TemplateException, IOException {
//        Writer writer = new StringWriter();
//        template.process(context, writer);
//        return writer.toString();
//    }
}
