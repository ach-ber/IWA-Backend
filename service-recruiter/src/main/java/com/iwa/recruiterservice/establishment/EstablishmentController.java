package com.iwa.recruiterservice.establishment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/establishments")
public class EstablishmentController {

    private final EstablishmentService service;

    public EstablishmentController(EstablishmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Establishment> getAllEstablishments() {
        return service.getEstablishments();
    }

    @GetMapping("/{id}")
    public Establishment getEstablishmentById(@PathVariable Long id) {
        return service.getEstablishmentById(id);
    }
}
