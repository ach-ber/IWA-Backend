package com.iwa.recruiterservice.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return service.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return service.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company newCompany) {
        Company res = service.createCompany(newCompany);

        if (res != null) {
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Company res = service.updateCompany(id, updatedCompany);

        if (res != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted = service.deleteCompanyById(id);

        if (deleted) {
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }
}
