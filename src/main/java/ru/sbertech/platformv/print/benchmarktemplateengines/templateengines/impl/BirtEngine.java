package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.birt.report.engine.api.*;

import ru.sbertech.platformv.print.benchmarktemplateengines.ScalaClass;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.FileReportEngine;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirtEngine implements FileReportEngine {
    private final ReportEngine engine;
    private final IRunAndRenderTask task;

    private BirtEngine (InputStream in, List<CompanyDto> companies) throws EngineException, JsonProcessingException {
        EngineConfig config = new EngineConfig();
        engine = new ReportEngine(config);

        IReportRunnable design = engine.openReportDesign(in);
        task = engine.createRunAndRenderTask(design);
        task.setParameterValues(setupContext(companies));
    }

    private Map<String, Object> setupContext(List<CompanyDto> companies) throws JsonProcessingException {
        Map<String, Object> params = new HashMap<>();
        params.put("companies", new ObjectMapper().writeValueAsString(companies));

        return params;
    }

    public static BirtEngine of(InputStream reportStream, List<CompanyDto> companies) throws EngineException, JsonProcessingException {
        return new BirtEngine(reportStream, companies);
    }

    @Override
    public void process(OutputStream stream) throws EngineException {
        PDFRenderOption options = new PDFRenderOption();
        options.setOutputFormat(PDFRenderOption.OUTPUT_FORMAT_PDF);
        options.setOutputStream(stream);
        task.setRenderOption(options);

        task.run();
        task.close();

        engine.destroy();
    }
}
