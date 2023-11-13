package com.iwa.jobservice.jobcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobcategoryRepository extends JpaRepository<Jobcategory, Long> {
}
