package ru.sbertech.platformv.print.benchmarktemplateengines

import scala.jdk.CollectionConverters._
import org.fusesource.scalate.{TemplateEngine, TemplateSource}
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto._
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.StringReportEngine

class ScalateEngine private (val templateSource: TemplateSource, val companies: java.util.List[CompanyDto]) extends StringReportEngine {

  override def process(): String = {
    try {
      val template = ScalateEngine.templateEngine.load(templateSource)

      val env: Map[String, AnyRef] = Map("companies" -> companies.asScala.toList)

      ScalateEngine.templateEngine.layout("", template, env)
    } catch {
      case ex: Exception =>
        println(s"Ошибка при рендеринге шаблона: ${ex.getMessage}")
        throw ex
    }
  }
}

object ScalateEngine {
  lazy val templateEngine: TemplateEngine = {
    val engine = new TemplateEngine()
    engine.allowReload = false
    engine.allowCaching = false
    engine
  }

  def ofSPP(report: String, companies: java.util.List[CompanyDto]): ScalateEngine = {
    val templateSource = TemplateSource.fromText("template.ssp", report)
    new ScalateEngine(templateSource, companies)
  }
}