package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines;

import java.io.IOException;
import java.util.List;

import freemarker.template.TemplateException;

//@Fork(5)
//@Warmup(iterations = 5)
//@Measurement(iterations = 10)
//@BenchmarkMode(Mode.Throughput)
//@OutputTimeUnit(TimeUnit.SECONDS)
//@State(Scope.Benchmark)
public interface ReportEngine<T,U> {
    void setup(List<T> values) throws IOException;
    U process() throws TemplateException, IOException;
}
