package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateException;
import liqp.Template;
import liqp.TemplateContext;
import liqp.TemplateParser;
import liqp.filters.Filter;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class LiqpEngine implements ReportEngine {

    private final Template template;
    private final List<OfficeDto> offices;

    public LiqpEngine(String report, List<OfficeDto> offices){
        this.offices = offices;

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
        return Map.of("offices", offices);
    }

    @Override
    public String process() throws TemplateException, IOException {
        return template.render(setupContext());
    }
}
