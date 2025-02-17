package ru.sbertech.platformv.print.benchmark.scala.templateengine.model

class SOfficeDto(val name: String,
                 val location: String,
                 val photo: SFileDto,
                 val employees: List[SEmployeeDto],
                 val resources: List[String])