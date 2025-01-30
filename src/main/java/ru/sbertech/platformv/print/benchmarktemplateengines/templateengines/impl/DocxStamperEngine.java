package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.wickedsource.docxstamper.DocxStamper;

import fr.opensagres.xdocreport.core.XDocReportException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.FileReportEngine;

public class DocxStamperEngine implements FileReportEngine {

    private final DocxStamper<Context> stamper;
    private final InputStream report;
    private final Context context;

    private DocxStamperEngine(List<CompanyDto> companies, InputStream report){
        this.report = report;
        this.context = new Context(companies);
        stamper = new DocxStamper<>();
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
        stamper.stamp(report, context, stream);
        stream.close();
    }
}
