package ru.sbertech.platformv.print.benchmark.kotlin.model

data class KOfficeDto(
    val name: String,
    val location: String,
    val photo: KFileDto,
    val employees: List<KEmployeeDto>,
    val resources: List<String>?
)