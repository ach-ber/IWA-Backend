package com.iwa.recruiterservice.company;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Company createCompany(Company company){
        return this.companyRepository.save(company);
    }

    public Long getNumberOfCompanies(){
        return this.companyRepository.count();
    }

    public List<Company> getCompanies(){
        return this.companyRepository.findAll();
    }

    public Company getCompanyById(Long id){
        return this.companyRepository.findById(id).orElse(null);
    }

    public void deleteCompanyById(Long id){
        this.companyRepository.deleteById(id);
    }
}
