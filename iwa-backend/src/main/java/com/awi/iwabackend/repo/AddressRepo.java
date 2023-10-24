package com.awi.iwabackend.repo;

import com.awi.iwabackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressRepo extends JpaRepository<Address, Integer> {
}
