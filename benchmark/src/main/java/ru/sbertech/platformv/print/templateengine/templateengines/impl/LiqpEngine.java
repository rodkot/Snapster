package ru.sbertech.platformv.print.templateengine.templateengines.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import freemarker.template.TemplateException;
import liqp.Template;
import liqp.TemplateContext;
import liqp.TemplateParser;
import liqp.filters.Filter;
import ru.sbertech.platformv.print.templateengine.model.CompanyDto;
import ru.sbertech.platformv.print.templateengine.templateengines.StringReportEngine;

public class LiqpEngine implements StringReportEngine {

    private final Template template;
    private final List<CompanyDto> companies;

    private LiqpEngine(String report, List<CompanyDto> companies){
        this.companies = companies;

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

    public static LiqpEngine of(String report, List<CompanyDto> companies){
        return new LiqpEngine(report, companies);
    }

    private Map<String, Object> setupContext() {
        return Map.of("companies", companies);
    }

    @Override
    public String process() throws TemplateException, IOException {
        return template.render(setupContext());
    }
}
