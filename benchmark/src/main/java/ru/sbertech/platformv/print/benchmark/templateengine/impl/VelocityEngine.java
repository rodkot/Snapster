package ru.sbertech.platformv.print.benchmark.templateengine.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.templateengine.StringReportEngine;


public class VelocityEngine implements StringReportEngine {

    private final String template;
    private final org.apache.velocity.app.VelocityEngine velocityEngine;
    private final List<CompanyDto> companies;

    private VelocityEngine(String report, List<CompanyDto> companies, Boolean cashing){
        this.companies = companies;
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader" +
                ".StringResourceLoader");
        if (cashing){
            properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        }

        template = report;
        velocityEngine = new org.apache.velocity.app.VelocityEngine(properties);
        velocityEngine.init();
    }

    public static VelocityEngine of(String report, List<CompanyDto> companies){
        return new VelocityEngine(report, companies, false);
    }

    public static VelocityEngine cachingOf(String report, List<CompanyDto> companies){
        return new VelocityEngine(report, companies, true);
    }

    private Context getContext(){
        VelocityContext context = new VelocityContext();

        context.put("companies", companies);
        context.put("colors", List.of( "#ff5733", "#33ff57" ,"#3357ff"));

        return context;
    }

    @Override
    public String process() throws TemplateException, IOException {
        Writer writer = new StringWriter();
        velocityEngine.evaluate(getContext(), writer, "velocity", template);

        return writer.toString();
    }
}
