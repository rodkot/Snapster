package ru.sbertech.platformv.print.benchmark.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sbertech.platformv.print.benchmark.domain.mapper.OfficeMapper;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmark.domain.model.entity.Office;
import ru.sbertech.platformv.print.benchmark.domain.repository.EmployeeRepository;
import ru.sbertech.platformv.print.benchmark.domain.repository.OfficeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final EmployeeRepository employeeRepository;
    private final OfficeMapper officeMapper;

    @Transactional
    public List<OfficeDto> loadAll(){
        List<Office> offices = officeRepository.findAll();

        return offices.stream().map(officeMapper::officeToDto).toList();
    }

}
