package ru.sbertech.platformv.print.templateengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbertech.platformv.print.templateengine.model.entity.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
