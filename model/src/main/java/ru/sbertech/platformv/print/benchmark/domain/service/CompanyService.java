package ru.sbertech.platformv.print.benchmark.domain.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbertech.platformv.print.benchmark.domain.mapper.CompanyMapper;
import ru.sbertech.platformv.print.benchmark.domain.model.dto.CompanyDto;
import ru.sbertech.platformv.print.benchmark.domain.repository.CompanyRepository;

import java.util.List;

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
