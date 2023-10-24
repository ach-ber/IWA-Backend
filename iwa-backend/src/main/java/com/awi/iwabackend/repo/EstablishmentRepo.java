package com.awi.iwabackend.repo;

import com.awi.iwabackend.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepo extends JpaRepository<Establishment, Integer> {
}
