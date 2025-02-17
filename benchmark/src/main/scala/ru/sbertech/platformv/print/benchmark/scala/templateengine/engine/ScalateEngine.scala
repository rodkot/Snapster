package ru.sbertech.platformv.print.benchmark.scala.templateengine.engine

import org.fusesource.scalate.{TemplateEngine, TemplateSource}
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto
import ru.sbertech.platformv.print.benchmark.scala.templateengine.engine.ScalateEngine.templateEngine
import ru.sbertech.platformv.print.benchmark.scala.templateengine.mapper.AsScalaCompanies.CompanyMapper

import scala.jdk.CollectionConverters.CollectionHasAsScala

class ScalateEngine private (val templateSource: TemplateSource, val env: Map[String, AnyRef], val cashing: Boolean) {

  templateEngine.allowCaching = cashing

  def process(): String = {
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
    val env: Map[String, AnyRef] = Map("companies" -> companies.asScala.map(company=>company.asScala).toList)
    new ScalateEngine(templateSource, env, true)
  }
}