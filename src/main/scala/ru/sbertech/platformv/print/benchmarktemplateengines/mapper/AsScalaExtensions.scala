package ru.sbertech.platformv.print.benchmarktemplateengines.mapper

import ru.sbertech.platformv.print.benchmarktemplateengines.model.{SCompanyDto, SEmployeeDto, SFileDto, SOfficeDto, SProjectDto}
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto._

import scala.jdk.CollectionConverters.CollectionHasAsScala

trait AsScalaExtensions {
  implicit class CompanyMapper(company: CompanyDto) {
    def asScala: SCompanyDto = new SCompanyDto(company.getName, company.getCode, company.getEmail,
      company.getLogo.asScala, company.getDirector.asScala, company.getGeneralOffice.asScala, company
        .getAdditionalOffices.asScala.map(office => office.asScala).toList)
  }

  implicit class FileMapper(file: FileDto) {
    def asScala: SFileDto = new SFileDto(file.getName)
  }

  implicit class EmployeeMapper(employee: EmployeeDto) {
    def asScala: SEmployeeDto = new SEmployeeDto(employee.getId, employee.getName, employee.getPosition, employee
      .getProject.asScala,
      employee.getSalary, employee.getExperience)
  }

  implicit class ProjectMapper(project: ProjectDto) {
    def asScala: SProjectDto = new SProjectDto(project.getName)
  }

  implicit class OfficeMapper(office: OfficeDto) {
    def asScala: SOfficeDto = new SOfficeDto(office.getName, office.getLocation, office.getPhoto.asScala, office
      .getEmployees.asScala.map(employee => employee.asScala).toList, office.getResources.asScala.toList)
  }
}