package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import static java.lang.System.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.wickedsource.docxstamper.DocxStamper;
import org.wickedsource.docxstamper.DocxStamperConfiguration;
import org.wickedsource.docxstamper.api.typeresolver.TypeResolverRegistry;
import org.wickedsource.docxstamper.processor.repeat.IRepeatProcessor;
import org.wickedsource.docxstamper.processor.repeat.RepeatProcessor;
import org.wickedsource.docxstamper.replace.typeresolver.FallbackResolver;

import fr.opensagres.xdocreport.core.XDocReportException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.FileReportEngine;

public class DocxStamperEngine implements FileReportEngine {

    private final DocxStamper stamper;
    private final InputStream report;
    private final Context context;

    private DocxStamperEngine(List<CompanyDto> companies, InputStream report){
        this.report = report;
        this.context = new Context(companies);
        stamper = new DocxStamperConfiguration()
                .addCommentProcessor(IRepeatProcessor.class, new RepeatProcessor(new TypeResolverRegistry(new FallbackResolver())))
                .build();
    }

    public static DocxStamperEngine of(List<CompanyDto> companies, InputStream report){
        return new DocxStamperEngine(companies, report);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Context{
        private List<CompanyDto> companies;
    }

    @Override
    public void process(OutputStream stream) throws IOException, XDocReportException, JRException {
        stamper.stamp(report, context, out);
        out.close();
    }
}
