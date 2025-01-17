package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
public class VelocityEngine implements ReportEngine {

    private String template;

    private org.apache.velocity.app.VelocityEngine velocityEngine;

    private final List<OfficeDto> offices;

    public VelocityEngine(List<OfficeDto> offices){
        this.offices = offices;
    }

    @Override
    public void setup(String report) {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader" +
                ".StringResourceLoader");
        template = report;
        velocityEngine = new org.apache.velocity.app.VelocityEngine(properties);
        velocityEngine.init();
    }


    private Context getContext(){
        VelocityContext context = new VelocityContext();

        context.put("offices", offices);
        context.put("colors", List.of( "#ff5733", "#33ff57" ,"#3357ff"));

        return context;
    }

    @Override
    public String process() throws TemplateException, IOException {
        Writer writer = new StringWriter();
        velocityEngine.evaluate(getContext(), writer, "velocity", template );

        return writer.toString();
    }
}
