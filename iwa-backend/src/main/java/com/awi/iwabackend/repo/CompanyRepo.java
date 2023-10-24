package com.awi.iwabackend.repo;

import com.awi.iwabackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
}
