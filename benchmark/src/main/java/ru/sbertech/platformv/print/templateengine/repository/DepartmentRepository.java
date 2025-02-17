package ru.sbertech.platformv.print.templateengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbertech.platformv.print.templateengine.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
