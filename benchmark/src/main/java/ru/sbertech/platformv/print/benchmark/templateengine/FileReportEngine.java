package ru.sbertech.platformv.print.benchmark.templateengine;

import java.io.IOException;
import java.io.OutputStream;

import fr.opensagres.xdocreport.core.XDocReportException;
import net.sf.jasperreports.engine.JRException;
import org.eclipse.birt.report.engine.api.EngineException;

public interface FileReportEngine {
    void process(OutputStream stream) throws IOException, XDocReportException, JRException, EngineException;
}
