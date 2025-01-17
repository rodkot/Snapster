package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class FreemarkerEngine implements ReportEngine {

    @Value("${templates.freemarker.report}")
    private String path;

    @Autowired
    private OfficeService officeService;

    private Map<String, Object> context;

    private Template template;


    @Override
    public void setup(String report) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setDefaultEncoding("UTF-8");

        template = new Template("offices",new StringReader(report), configuration);

        context = setupContext();
    }

    private HashMap<String, Object> setupContext(){
        var context = new HashMap<String, Object>();

        var offices = officeService.loadAll();

        context.put("offices", offices);

        return context;
    }

    @Override
    public String process() throws TemplateException, IOException {
        Writer writer = new StringWriter();
        template.process(context, writer);
        return writer.toString();
    }
}
