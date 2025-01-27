package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.MruCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.StringReportEngine;


public class FreemarkerEngine implements StringReportEngine {

    private final List<CompanyDto> companies;
    private final Map<String, Object> context;
    private final Template template;

    private FreemarkerEngine(String report, List<CompanyDto> companies, Boolean cashing) throws IOException {
        this.companies = companies;
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        if (cashing){
            configuration.setCacheStorage(new MruCacheStorage(20,250));
        }
        configuration.setDefaultEncoding("UTF-8");

        template = new Template("offices", new StringReader(report), configuration);

        context = setupContext();
    }

    public static FreemarkerEngine of(String report, List<CompanyDto> companies) throws IOException {
        return new FreemarkerEngine(report, companies, false);
    }

    public static FreemarkerEngine cachingOf(String report, List<CompanyDto> companies) throws IOException {
        return new FreemarkerEngine(report, companies, true);
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
