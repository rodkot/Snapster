package ru.sbertech.platformv.print.benchmarktemplateengines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyRepository,Long> {
}
