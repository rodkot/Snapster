package ru.sbertech.platformv.print.benchmark.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Employee;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Office;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByOffice(Office office);
}
