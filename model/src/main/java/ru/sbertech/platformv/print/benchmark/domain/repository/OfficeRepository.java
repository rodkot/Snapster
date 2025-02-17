package ru.sbertech.platformv.print.benchmark.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office,Long> {
}
