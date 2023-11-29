package com.iwa.recruiterservice.company;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Company> getCompanyById(Long id){
        return this.companyRepository.findById(id);
    }

    @Transactional
    public boolean deleteCompanyById(Long id){
        if (this.companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Company> updateCompany(Long id, Company updatedCompany) {
        Optional<Company> existingCompany = companyRepository.findById(id);

        if (existingCompany != null) {
            updatedCompany.setId(id);
            return Optional.of(companyRepository.save(updatedCompany));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteAll() {
        companyRepository.deleteAll();
    }
}
