package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines;

import java.io.IOException;
import java.io.OutputStream;

import fr.opensagres.xdocreport.core.XDocReportException;

public interface FileReportEngine {
    void process(OutputStream stream) throws IOException, XDocReportException;
}
