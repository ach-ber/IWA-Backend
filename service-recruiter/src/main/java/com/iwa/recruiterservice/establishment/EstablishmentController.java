package com.iwa.recruiterservice.establishment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Establishment> getEstablishmentById(@PathVariable Long id) {
        return service.getEstablishmentById(id)
            .map(establishment -> new ResponseEntity<>(establishment, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Establishment> createEstablishment(@RequestBody Establishment newEstablishment) {
        Establishment result = service.createEstablishment(newEstablishment);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Establishment> updateEstablishment(@PathVariable Long id, @RequestBody Establishment updatedEstablishment) {
        return service.updateEstablishment(id, updatedEstablishment)
            .map(establishment -> new ResponseEntity<>(establishment, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEstablishment(@PathVariable Long id) {
        boolean deleted = service.deleteEstablishmentById(id);

        if (deleted) {
            return new ResponseEntity<>("Establishment deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Establishment not found", HttpStatus.NOT_FOUND);
        }
    }
}
