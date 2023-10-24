package com.awi.iwabackend.repo;

import com.awi.iwabackend.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecruiterRepo extends JpaRepository<Recruiter, Integer> {
}
