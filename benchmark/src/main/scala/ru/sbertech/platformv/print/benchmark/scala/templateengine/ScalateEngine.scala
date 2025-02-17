package ru.sbertech.platformv.print.benchmark.scala.templateengine

import org.fusesource.scalate.{TemplateEngine, TemplateSource}
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto
import ru.sbertech.platformv.print.benchmark.scala.mapper.AsScalaCompanies.CompanyMapper
import ru.sbertech.platformv.print.benchmark.scala.model.SCompanyDto
import ru.sbertech.platformv.print.benchmark.scala.templateengine.ScalateEngine.templateEngine

import scala.jdk.CollectionConverters.CollectionHasAsScala

class ScalateEngine private(val uri: String, val report: String, val companies: List[SCompanyDto], val cashing: Boolean) {

  templateEngine.allowCaching = cashing

  def process(): String = {
    try {
      val templateSource = TemplateSource.fromText("template.ssp", report)
      val template = ScalateEngine.templateEngine.load(templateSource)

      val env: Map[String, AnyRef] = Map("companies" -> companies)

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

  def of(uri: String, report: String, companies: java.util.List[CompanyDto]): ScalateEngine = {
    new ScalateEngine(uri, report, companies.asScala.map(company=>company.asScala).toList, false)
  }

  def cachingOf(uri: String, report: String, companies: java.util.List[CompanyDto]): ScalateEngine = {
    new ScalateEngine(uri, report, companies.asScala.map(company=>company.asScala).toList, false)
  }
}