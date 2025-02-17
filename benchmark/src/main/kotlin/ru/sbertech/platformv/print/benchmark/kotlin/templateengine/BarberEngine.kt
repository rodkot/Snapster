package ru.sbertech.platformv.print.benchmark.kotlin.templateengine

import app.cash.barber.Barber
import app.cash.barber.BarbershopBuilder
import app.cash.barber.getBarber
import app.cash.barber.locale.Locale.Companion.EN_US
import app.cash.barber.models.Document
import app.cash.barber.models.DocumentData
import app.cash.barber.models.DocumentTemplate
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto
import ru.sbertech.platformv.print.benchmark.kotlin.mapper.AsKotlinCompanies.asKotlin
import ru.sbertech.platformv.print.benchmark.kotlin.model.KCompanyDto
import ru.sbertech.platformv.print.benchmark.templateengine.StringReportEngine

class BarberEngine private constructor(val report: String, val companies: List<CompanyDto>) : StringReportEngine {

    private val context: Context = Context(companies.map { it.asKotlin() })
    private val barber: Barber<Companies>

    init {

        val companiesDocumentTemplate = DocumentTemplate(
            fields = mapOf("companies" to report),
            source = Context::class,
            targets = setOf(Companies::class),
            locale = EN_US
        )

        barber = BarbershopBuilder()
            .installDocument<Companies>()
            .installDocumentTemplate<Context>(companiesDocumentTemplate)
            .build().getBarber<Context, Companies>()

    }

    private data class Companies(val companies: String) : Document
    private data class Context(val companies: List<KCompanyDto>) : DocumentData


    companion object {
        fun of(report: String, companies: List<CompanyDto>): BarberEngine {
            return BarberEngine(report, companies)
        }
    }

    override fun process(): String {
        return barber.render(context, EN_US).companies
    }
}