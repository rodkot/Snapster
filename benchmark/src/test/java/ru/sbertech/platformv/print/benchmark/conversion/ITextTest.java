package ru.sbertech.platformv.print.benchmark.conversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import ru.sbertech.platformv.print.benchmark.common.SetupTest;

@ContextConfiguration(classes = ResourceConfig.class)
public class ITextTest extends SetupTest {

    @Autowired
    private String report;

    @Autowired
    @Qualifier("outputIText")
    private File output;

    @Test
    public void conversionTest() throws FileNotFoundException {
        ITextConverter converter = new ITextConverter(report);

        converter.convert(new FileOutputStream(output));
    }
}
