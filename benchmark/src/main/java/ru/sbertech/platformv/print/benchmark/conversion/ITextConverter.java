package ru.sbertech.platformv.print.benchmark.conversion;

import java.io.OutputStream;

import com.itextpdf.html2pdf.HtmlConverter;

public class ITextConverter {

    private final String report;

    public ITextConverter(String report){
        this.report = report;
    }

    public void convert(OutputStream outputStream) {
        HtmlConverter.convertToPdf(report, outputStream);
    }
}
