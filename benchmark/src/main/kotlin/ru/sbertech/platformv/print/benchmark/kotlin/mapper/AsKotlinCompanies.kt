package ru.sbertech.platformv.print.benchmark.kotlin.mapper

import ru.sbertech.platformv.print.benchmark.domain.model.dto.*
import ru.sbertech.platformv.print.benchmark.kotlin.model.*

object AsKotlinCompanies {
    fun CompanyDto.asKotlin(): KCompanyDto {
        return KCompanyDto(name, code, email, logo.asKotlin(), director.asKotlin(), generalOffice.asKotlin(),
            additionalOffices.map { it.asKotlin() })
    }

    private fun FileDto.asKotlin(): KFileDto {
        return KFileDto(name)
    }

    private fun EmployeeDto.asKotlin(): KEmployeeDto {
        return KEmployeeDto(id, name, position, project.asKotlin(), salary, experience)
    }

    private fun ProjectDto.asKotlin(): KProjectDto {
        return KProjectDto(name)
    }

    private fun OfficeDto.asKotlin(): KOfficeDto {
        return KOfficeDto(name, location, photo.asKotlin(), employees.map { it.asKotlin() }, resources)
    }
}