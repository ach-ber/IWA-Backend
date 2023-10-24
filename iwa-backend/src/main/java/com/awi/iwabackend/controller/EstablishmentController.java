package com.awi.iwabackend.controller;

import com.awi.iwabackend.model.Establishment;
import com.awi.iwabackend.repo.EstablishmentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establishments")
public class EstablishmentController {

    private final EstablishmentRepo repository;

    public EstablishmentController(EstablishmentRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Establishment> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Establishment findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Establishment save(Establishment establishment) {
        return repository.save(establishment);
    }

    @DeleteMapping
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
