package ru.sbertech.platformv.print.benchmarktemplateengines

import scala.collection.mutable
import scala.collection.JavaConverters._
import org.fusesource.scalate.{TemplateEngine, TemplateSource}
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto._
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.StringReportEngine

class ScalateSspEngine(val report: String, val companies: java.util.List[CompanyDto]) extends StringReportEngine {

  override def process(): String = {
    val templateEngine = new TemplateEngine()
    templateEngine.allowReload = false
    templateEngine.allowCaching = false

    val templateSource = TemplateSource.fromText("test.ssp", report)
    val template = templateEngine.load(templateSource)

    val env = scala.Predef.Map("companies" -> companies.asScala.toList)

    templateEngine.layout("", template, env)
  }
}