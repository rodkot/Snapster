package ru.sbertech.platformv.print.benchmark.kotlin.model

data class KCompanyDto (val name: String,
                        val code: String,
                        val email: String,
                        val logo: KFileDto,
                        val director: KEmployeeDto,
                        val generalOffice: KOfficeDto,
                        val additionalOffices: List<KOfficeDto>)