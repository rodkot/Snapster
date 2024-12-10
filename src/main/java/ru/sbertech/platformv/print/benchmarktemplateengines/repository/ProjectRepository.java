package ru.sbertech.platformv.print.benchmarktemplateengines.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sbertech.platformv.print.benchmarktemplateengines.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Long, Project> {
}
