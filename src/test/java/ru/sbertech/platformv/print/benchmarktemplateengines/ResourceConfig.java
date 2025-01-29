package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.testcontainers.shaded.org.bouncycastle.est.LimitedSource;

import ru.sbertech.platformv.print.benchmarktemplateengines.configuration.BenchmarkTemplateEnginesAutoConfiguration;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.CompanyService;
import ru.sbertech.platformv.print.benchmarktemplateengines.service.ResourceResolverService;

@TestConfiguration
public class ResourceConfig {

    @Bean
    public ClassLoader resourceLoader(){
        return ResourceConfig.class.getClassLoader();
    }

    @Bean
    @Scope("prototype")
    public List<CompanyDto> companies(CompanyService companyService){
        return companyService.loadAll();
    }

    @Bean
    public File reportXDoc(@Value("${templates.xdoc.report}") String path) throws URISyntaxException {
        return new File(Objects.requireNonNull(ResourceConfig.class.getResource(path)).toURI());
    }

    @Bean
    public File outputXDoc(@Value("${templates.xdoc.output}") String path) {
        return new File(path);
    }


    @Bean
    public File reportJasper(@Value("${templates.jasper.report}") String path) throws URISyntaxException {
        return new File(Objects.requireNonNull(ResourceConfig.class.getResource(path)).toURI());
    }

    @Bean
    public File outputJasper(@Value("${templates.jasper.output}") String path) {
        return new File(path);
    }

    @Bean
    public String reportFreemarker(@Value("${templates.freemarker.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputFreemarker(@Value("${templates.freemarker.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportHttl(@Value("${templates.httl.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputHttl(@Value("${templates.httl.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportJinjava(@Value("${templates.jinjava.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputJinjava(@Value("${templates.jinjava.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportLiqp(@Value("${templates.liqp.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputLiqp(@Value("${templates.liqp.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportMustache(@Value("${templates.mustache.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputMustache(@Value("${templates.mustache.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportPebble(@Value("${templates.pebble.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputPebble(@Value("${templates.pebble.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportThymeleaf(@Value("${templates.thymeleaf.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputThymeleaf(@Value("${templates.thymeleaf.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportTrimou(@Value("${templates.trimou.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputTrimou(@Value("${templates.trimou.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String reportVelocity(@Value("${templates.velocity.report}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }

    @Bean
    public String outputVelocity(@Value("${templates.velocity.output}") String path, ResourceResolverService resourceResolverService) throws IOException {
        return resourceResolverService.readExpectedOutputResource(path);
    }
}
