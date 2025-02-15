package ru.sbertech.platformv.print.benchmarktemplateengines.templateengines

import org.fusesource.scalate.{TemplateEngine, TemplateSource}
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto._
import ru.sbertech.platformv.print.benchmarktemplateengines.templateengines.ScalateEngine.templateEngine
import ru.sbertech.platformv.print.benchmarktemplateengines.model._
import ru.sbertech.platformv.print.benchmarktemplateengines.mapper.AsScalaCompanies.CompanyMapper

import scala.jdk.CollectionConverters._

class ScalateEngine private (val templateSource: TemplateSource, val env: Map[String, AnyRef], val cashing: Boolean)
  extends
  StringReportEngine {

  templateEngine.allowCaching = cashing

  override def process(): String = {
    try {
      val template = ScalateEngine.templateEngine.load(templateSource)

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
    engine
  }

  def ofSPP(report: String, companies: java.util.List[CompanyDto]): ScalateEngine = {
    val templateSource = TemplateSource.fromText("template.ssp", report)
    val env: Map[String, AnyRef] = Map("companies" -> companies.asScala.map(company=>company.asScala).toList)
    new ScalateEngine(templateSource, env, false)
  }

  def ofCashingSPP(report: String, companies: java.util.List[CompanyDto]): ScalateEngine = {
    val templateSource = TemplateSource.fromText("template.ssp", report)
    val env: Map[String, AnyRef] = Map("companies" -> companies.asScala.toList)
    new ScalateEngine(templateSource, env, true)
  }
}