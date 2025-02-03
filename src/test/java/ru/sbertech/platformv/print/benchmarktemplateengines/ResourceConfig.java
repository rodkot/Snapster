package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

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
    public File reportBirt(@Value("${templates.birt.report}") String path,
                                  ResourceResolverService resourceResolverService) throws URISyntaxException {
        return resourceResolverService.getFileFromResource(path);
    }

    @Bean
    public File outputBirt(@Value("${templates.birt.output}") String path) {
        return new File(path);
    }


    @Bean
    public File reportDocxStamper(@Value("${templates.docx-stamper.report}") String path,
            ResourceResolverService resourceResolverService) throws URISyntaxException {
        return resourceResolverService.getFileFromResource(path);
    }

    @Bean
    public File outputDocxStamper(@Value("${templates.docx-stamper.output}") String path) {
        return new File(path);
    }


    @Bean
    public File reportXDoc(@Value("${templates.xdoc.report}") String path, ResourceResolverService resourceResolverService) throws URISyntaxException {
        return resourceResolverService.getFileFromResource(path);
    }

    @Bean
    public File outputXDoc(@Value("${templates.xdoc.output}") String path) {
        return new File(path);
    }


    @Bean
    public File reportJRXML(@Value("${templates.jasper.jrxml}") String path,
            ResourceResolverService resourceResolverService) throws URISyntaxException {
        return resourceResolverService.getFileFromResource(path);
    }

    @Bean
    public File reportJasper(@Value("${templates.jasper.report}") String path,
            ResourceResolverService resourceResolverService) throws URISyntaxException {
        return resourceResolverService.getFileFromResource(path);
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
    public File reportFizzed(@Value("${templates.fizzed.report}") String path,
            ResourceResolverService resourceResolverService) throws URISyntaxException {
        return resourceResolverService.getFileFromResource(path);
    }

    @Bean
    public String outputFizzed(@Value("${templates.fizzed.output}") String path,
            ResourceResolverService resourceResolverService) throws IOException {
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
