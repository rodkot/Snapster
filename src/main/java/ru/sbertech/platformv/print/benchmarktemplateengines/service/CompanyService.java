package ru.sbertech.platformv.print.benchmarktemplateengines.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.sbertech.platformv.print.benchmarktemplateengines.mapper.CompanyMapper;
import ru.sbertech.platformv.print.benchmarktemplateengines.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmarktemplateengines.repository.CompanyRepository;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Transactional
    public List<CompanyDto> loadAll(){
        var companies = companyRepository.findAll();
        return companies.stream().map(companyMapper::companyToDto).toList();
    }
}
