package ru.sbertech.platformv.print.benchmarktemplateengines.model

class SCompanyDto(val name: String,
                  val code: String,
                  val email: String,
                  val logo: SFileDto,
                  val director: SEmployeeDto,
                  val generalOffice: SOfficeDto,
                  val additionalOffices: List[SOfficeDto])