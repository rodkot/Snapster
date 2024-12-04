package ru.sbertech.platformv.print.benchmarktemplateengines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import freemarker.template.TemplateException;

public class ExpectedOutputTest {
    @BeforeClass
    public static void beforeClass() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void testFreemarkerOutput() throws IOException, TemplateException {
        Freemarker freemarker = new Freemarker();
        freemarker.setup();
        assertOutput(freemarker.benchmark());
    }

    private void assertOutput(final String output) throws IOException {
        assertEquals(readExpectedOutputResource(), output.replaceAll("\\s", ""));
    }

    private String readExpectedOutputResource() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ExpectedOutputTest.class.getResourceAsStream("/output.html"))))) {
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line);
            }
        }
        // Remove all whitespaces
        return builder.toString().replaceAll("\\s", "");
    }
}
