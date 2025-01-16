package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;
import liqp.Template;
import liqp.TemplateContext;
import liqp.TemplateParser;
import liqp.filters.Filter;
import liqp.filters.Modulo;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.OfficeService;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

@Service
@RequiredArgsConstructor
public class LiqpEngine implements ReportEngine {
    @Autowired
    private OfficeService officeService;

    private Template template;

    @Override
    public void setup(String report) throws IOException {

        TemplateParser parser = new TemplateParser.Builder().withFilter(new Filter("color") {
            @Override
            public Object apply(Object value, TemplateContext context, Object... params) {
                var text = super.asString(value, context);
                int index = params.length == 0 ? 1 : super.asNumber(params[0]).intValue();
                var colors = List.of( "#ff5733", "#33ff57", "#3357ff");

                return text + colors.get(index % colors.size());
            }
        }).build();
        template = parser.parse(report);

    }

    private Map<String, Object> setupContext() {
        var offices = officeService.loadAll();
        return Map.of("offices", offices);
    }

    @Override
    public String process() throws TemplateException, IOException {
        return template.render(setupContext());
    }
}
