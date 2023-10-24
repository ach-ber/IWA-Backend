package com.awi.iwabackend.controller;

import com.awi.iwabackend.model.Address;
import com.awi.iwabackend.repo.AddressRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressRepo repository;

    public AddressController(AddressRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Address> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Address save(Address address) {
        return repository.save(address);
    }

    @DeleteMapping
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
