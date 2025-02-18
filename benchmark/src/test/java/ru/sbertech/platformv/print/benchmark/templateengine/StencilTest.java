package ru.sbertech.platformv.print.benchmark.templateengine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;

public class StencilTest extends ExpectedOutputTest {

    @Autowired
    private List<CompanyDto> companies;

    @Autowired
    @Qualifier("reportStencil")
    private File report;

    @Autowired
    @Qualifier("outputStencil")
    private File output;

    @Test
    public void testOutput(){
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ru.sbertech.platformv.print.benchmark.clojure.StencilEngine"));

        IFn renderTemplate = Clojure.var("ru.sbertech.platformv.print.benchmark.clojure.StencilEngine", "process");

        String templatePath = report.getAbsolutePath();
        String outputPath = output.getAbsolutePath();

        Map<String, Object> data = new HashMap<>();
        data.put("companies", companies.stream().map(CompanyDto::getMap).toList());

        renderTemplate.invoke(templatePath, outputPath, data);
    }
}
