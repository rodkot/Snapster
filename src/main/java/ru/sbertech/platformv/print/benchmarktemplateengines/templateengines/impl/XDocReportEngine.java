package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import freemarker.template.TemplateException;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ReportEngine;

public class XDocReportEngine implements ReportEngine {
    private final IContext context;
    private final IXDocReport report;

    private XDocReportEngine(InputStream in, List<CompanyDto> companies) throws IOException, XDocReportException {
        report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
        FieldsMetadata metadata = report.createFieldsMetadata();
        metadata.load("companies", CompanyDto.class, true);

        context = report.createContext();
        context.put("companies", companies);
    }

    public static XDocReportEngine of(InputStream in, List<CompanyDto> companies) throws IOException,
            XDocReportException {
        return new XDocReportEngine(in, companies);
    }


    @Override
    public String process() throws TemplateException, IOException, ParseException, XDocReportException {
        OutputStream out = new FileOutputStream("DocxProjectWithFreemarkerList_Out.docx");
        report.process(context, out);
        return null;
    }
}
