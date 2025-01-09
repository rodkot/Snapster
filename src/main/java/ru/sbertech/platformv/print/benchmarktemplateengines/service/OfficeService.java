package ru.sbertech.platformv.print.benchmarktemplateengines.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.sbertech.platformv.print.benchmarktemplateengines.mapper.OfficeMapper;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.EmployeeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.OfficeDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.entity.Office;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.EmployeeRepository;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.OfficeRepository;

@Service
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final EmployeeRepository employeeRepository;
    private final OfficeMapper officeMapper;

    public List<OfficeDto> loadAll(){
        List<Office> offices = officeRepository.findAll();

        return offices.stream().map(office -> officeMapper.officeToDto(office,
                employeeRepository.findAllByOffice(office))).toList();
    }

}
