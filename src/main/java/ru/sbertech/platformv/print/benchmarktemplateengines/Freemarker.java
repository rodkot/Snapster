package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Freemarker extends BaseBenchmark{

    @Value("${templates.freemarker.path}")
    private String path;

    private Map<String, Object> context;

    private Template template;

    @Setup
    public void setup() throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), "/"));
        template = configuration.getTemplate(path);
        this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws TemplateException, IOException {
        Writer writer = new StringWriter();
        template.process(context, writer);
        return writer.toString();
    }
}
