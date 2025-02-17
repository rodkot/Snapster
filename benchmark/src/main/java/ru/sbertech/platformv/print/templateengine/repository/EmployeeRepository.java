package ru.sbertech.platformv.print.templateengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbertech.platformv.print.templateengine.model.entity.Employee;
import ru.sbertech.platformv.print.templateengine.model.entity.Office;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByOffice(Office office);
}
