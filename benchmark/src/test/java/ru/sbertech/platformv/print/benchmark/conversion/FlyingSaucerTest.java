package ru.sbertech.platformv.print.benchmark.conversion;

import com.lowagie.text.DocumentException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import ru.sbertech.platformv.print.benchmark.common.SetupTest;
import ru.sbertech.platformv.print.benchmark.domain.service.ResourceResolverService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;

@ContextConfiguration(classes = ResourceConfig.class)
public class FlyingSaucerTest extends SetupTest {

    @Autowired
    private String report;

    @Autowired
    @Qualifier("outputFlyingSaucer")
    private File output;

    @Autowired
    private ResourceResolverService resourceResolverService;

    @Autowired
    private Path resourcePath;

    @Test
    public void conversionTest() throws FileNotFoundException, DocumentException {
        FlyingSaucerConverter converter = new FlyingSaucerConverter(report,resourcePath.toString(),
                resourceResolverService);

        converter.convert(new FileOutputStream(output));
    }
}
