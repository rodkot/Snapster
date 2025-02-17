package ru.sbertech.platformv.print.templateengine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.templateengine.mapper.OfficeMapper;
import ru.sbertech.platformv.print.templateengine.model.OfficeDto;
import ru.sbertech.platformv.print.templateengine.model.entity.Office;
import ru.sbertech.platformv.print.templateengine.repository.EmployeeRepository;
import ru.sbertech.platformv.print.templateengine.repository.OfficeRepository;

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
