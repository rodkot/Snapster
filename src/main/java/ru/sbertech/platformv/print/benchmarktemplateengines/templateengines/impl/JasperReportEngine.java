package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import fr.opensagres.xdocreport.core.XDocReportException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.FileReportEngine;

public class JasperReportEngine implements FileReportEngine {
    private final JRPdfExporter pdfExporter;
    private final JasperPrint jasperPrint;

    private JasperReportEngine(List<CompanyDto> companies, InputStream report) throws JRException {
        JRBeanCollectionDataSource companiesDS = new JRBeanCollectionDataSource(companies);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companies", companies);

        jasperPrint = JasperFillManager.fillReport(report, parameters, companiesDS);
        pdfExporter = new JRPdfExporter();

    }

    public static JasperReportEngine of(InputStream report, List<CompanyDto> companies) throws JRException {
        return new JasperReportEngine(companies, report);
    }

    @Override
    public void process(OutputStream stream) throws IOException, XDocReportException, JRException {
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));
        pdfExporter.exportReport();
    }
}
