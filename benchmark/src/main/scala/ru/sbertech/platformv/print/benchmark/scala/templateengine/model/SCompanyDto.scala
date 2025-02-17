package ru.sbertech.platformv.print.benchmark.scala.templateengine.model

class SCompanyDto(val name: String,
                  val code: String,
                  val email: String,
                  val logo: SFileDto,
                  val director: SEmployeeDto,
                  val generalOffice: SOfficeDto,
                  val additionalOffices: List[SOfficeDto])