package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;


public class FreemarkerEngine implements ReportEngine {

    private final List<CompanyDto> companies;
    private final Map<String, Object> context;
    private final Template template;

    public FreemarkerEngine(String report, List<CompanyDto> companies) throws IOException {
        this.companies = companies;
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setDefaultEncoding("UTF-8");

        template = new Template("offices", new StringReader(report), configuration);

        context = setupContext();
    }

    private HashMap<String, Object> setupContext(){
        var context = new HashMap<String, Object>();
        context.put("companies", companies);

        return context;
    }

    @Override
    public String process() throws TemplateException, IOException {
        Writer writer = new StringWriter();
        template.process(context, writer);
        return writer.toString();
    }
}
