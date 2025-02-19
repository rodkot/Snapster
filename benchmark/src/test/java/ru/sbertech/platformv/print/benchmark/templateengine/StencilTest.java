package ru.sbertech.platformv.print.benchmark.templateengine;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class StencilTest extends ExpectedOutputTest {

    @Autowired
    @Qualifier("companiesMap")
    private Map<String,Object> companies;

    @Autowired
    @Qualifier("reportStencil")
    private File report;

    @Autowired
    @Qualifier("outputStencil")
    private File output;

    private final IFn engine;

    public StencilTest(){
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ru.sbertech.platformv.print.benchmark.clojure.StencilEngine"));

        engine = Clojure.var("ru.sbertech.platformv.print.benchmark.clojure.StencilEngine", "process");
    }

    @Test
    public void testOutput(){
        String templatePath = report.getAbsolutePath();
        String outputPath = output.getAbsolutePath();

        engine.invoke(templatePath, outputPath, companies);
    }
}
