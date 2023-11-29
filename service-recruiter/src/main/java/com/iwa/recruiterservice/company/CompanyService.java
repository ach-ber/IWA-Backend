package com.iwa.recruiterservice.company;

import com.iwa.recruiterservice.address.Address;
import com.iwa.recruiterservice.address.AddressService;
import com.iwa.recruiterservice.dto.AddressDTO;
import com.iwa.recruiterservice.dto.CompanyDTO;
import com.iwa.recruiterservice.dto.EstablishmentDTO;
import com.iwa.recruiterservice.establishment.EstablishmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private final EstablishmentService establishmentService;

    private final AddressService addressService;

    public CompanyService(CompanyRepository companyRepository, EstablishmentService establishmentService, AddressService addressService) {
        this.companyRepository = companyRepository;
        this.establishmentService = establishmentService;
        this.addressService = addressService;
    }

    @Transactional
    public Company createCompany(Company company){
        return this.companyRepository.save(company);
    }

    public Long getNumberOfCompanies(){
        return this.companyRepository.count();
    }

    public List<CompanyDTO> getCompanies(){
        List<Company> companies = this.companyRepository.findAll();
        if (companies.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<CompanyDTO> companiesDTO = new ArrayList<>();
            for (Company company : companies) {
                getCompanyById(company.getId()).ifPresent(companiesDTO::add);
            }
            return companiesDTO;
        }
    }

    public Optional<CompanyDTO> getCompanyById(Long id){
        Optional<Company> company = this.companyRepository.findById(id);
        if (company.isPresent()) {
            Company existingCompany = company.get();
            List<EstablishmentDTO> establishments = new ArrayList<>();
            for (Long establishmentId : company.get().getEstablishments()) {
                establishmentService.getEstablishmentById(establishmentId).ifPresent(establishment -> {
                    Address address = addressService.getAddressById(establishment.getAddressId()).get();
                    AddressDTO addressDTO = new AddressDTO(address.getId(), address.getStreetNum(), address.getStreet(), address.getComplement(), address.getCity(), address.getZipCode(), address.getCountry());
                    establishments.add(new EstablishmentDTO(establishment.getId(), establishment.getName(), establishment.getSiret(), addressDTO));
                });
            }
            return Optional.of(new CompanyDTO(existingCompany.getId(), existingCompany.getName(), existingCompany.getSiren(), establishments));
        } else {
            return Optional.empty();
        }
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
