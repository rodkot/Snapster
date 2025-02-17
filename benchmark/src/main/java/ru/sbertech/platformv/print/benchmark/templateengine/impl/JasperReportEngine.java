package ru.sbertech.platformv.print.benchmark.templateengine.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.opensagres.xdocreport.core.XDocReportException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.templateengine.FileReportEngine;

public class JasperReportEngine implements FileReportEngine {
    private final JRPdfExporter pdfExporter;
    private final JasperPrint jasperPrint;

    private JasperReportEngine(List<CompanyDto> companies, JasperReport report) throws JRException {
        jasperPrint = JasperFillManager.fillReport(report, setupContext(companies),
                new JREmptyDataSource());
        pdfExporter = new JRPdfExporter();
    }

    public static JasperReportEngine ofFormatReport(File reportJrxml, List<CompanyDto> companies) throws JRException,
            FileNotFoundException {
        return new JasperReportEngine(companies, JasperCompileManager.compileReport(new FileInputStream(reportJrxml)) );
    }

    public static JasperReportEngine ofCompileReport(File report, List<CompanyDto> companies) throws JRException {
        return new JasperReportEngine(companies, (JasperReport) JRLoader.loadObject(report));
    }

    private Map<String, Object> setupContext(List<CompanyDto> companies){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companies", companies);
        return parameters;
    }

    @Override
    public void process(OutputStream stream) throws IOException, XDocReportException, JRException {
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));
        pdfExporter.exportReport();
    }
}
