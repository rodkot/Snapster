package ru.sbertech.platformv.print.benchmarktemplateengines

import fr.opensagres.xdocreport.core.XDocReportException
import freemarker.template.TemplateException
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.impl.BarberEngine
import java.io.IOException
import java.text.ParseException

class BarberTest: ExpectedOutputTest() {

    @Autowired
    @Qualifier("reportBarber")
    private val report: String? = null

    @Autowired
    @Qualifier("outputBarber")
    private val output: String? = null

    @Autowired
    private val companies: List<CompanyDto>? = null


    @Test
    @Throws(
        IOException::class,
        TemplateException::class,
        ParseException::class,
        XDocReportException::class
    )
    fun testOutput() {
        val engine = BarberEngine.of(report!!, companies!!)
        assertOutput(output, engine.process())
    }
}