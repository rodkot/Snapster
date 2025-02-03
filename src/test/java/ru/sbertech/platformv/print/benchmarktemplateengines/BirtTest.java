package ru.sbertech.platformv.print.benchmarktemplateengines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;

import java.io.File;
import java.util.List;

public class BirtTest extends ExpectedOutputTest {

    @Autowired
    private List<CompanyDto> companies;

    @Autowired
    @Qualifier("reportBirt")
    private File report;

    @Autowired
    @Qualifier("outputBirt")
    private File output;

}