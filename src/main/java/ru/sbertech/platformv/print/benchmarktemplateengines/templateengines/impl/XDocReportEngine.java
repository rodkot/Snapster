package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.FileReportEngine;

public class XDocReportEngine implements FileReportEngine {
    private final IContext context;
    private final IXDocReport report;

    private XDocReportEngine(InputStream in, List<CompanyDto> companies) throws IOException, XDocReportException {
        report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

        context = setupContext(companies);
    }

    private IContext setupContext(List<CompanyDto> companies) throws XDocReportException {
        FieldsMetadata metadata = report.createFieldsMetadata();
        metadata.load("companies", CompanyDto.class, true);

        var context = report.createContext();
        context.put("companies", companies);

        return context;
    }

    public static XDocReportEngine of(InputStream in, List<CompanyDto> companies) throws IOException,
            XDocReportException {
        return new XDocReportEngine(in, companies);
    }

    @Override
    public void process(OutputStream out) throws IOException, XDocReportException {
        report.process(context, out);
    }
}
