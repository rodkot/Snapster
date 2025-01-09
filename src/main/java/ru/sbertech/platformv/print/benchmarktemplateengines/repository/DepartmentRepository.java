package ru.sbertech.platformv.print.benchmarktemplateengines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
