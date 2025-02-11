package ru.sbertech.platformv.print.benchmarktemplateengines

import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto._
import scala.jdk.CollectionConverters._
import scala.collection.mutable

class ScalateEngine(val companies: java.util.List[CompanyDto]){

  def greet(): Unit = {
    val scalaSeq = companies.asScala
    for (company <- scalaSeq)
      println(company.getGeneralOffice)
  }
}