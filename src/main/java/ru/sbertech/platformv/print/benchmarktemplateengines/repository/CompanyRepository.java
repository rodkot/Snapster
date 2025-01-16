package ru.sbertech.platformv.print.benchmarktemplateengines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
