package com.iwa.recruiterservice.establishment;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablishmentService {

    private EstablishmentRepository establishmentRepository;

    public EstablishmentService(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    @Transactional
    public Establishment createEstablishment(Establishment establishment){
        return this.establishmentRepository.save(establishment);
    }

    public Long getNumberOfEstablishments(){
        return this.establishmentRepository.count();
    }

    public List<Establishment> getEstablishments(){
        return this.establishmentRepository.findAll();
    }

    public Establishment getEstablishmentById(Long id){
        return this.establishmentRepository.findById(id).orElse(null);
    }

    public void deleteEstablishmentById(Long id){
        this.establishmentRepository.deleteById(id);
    }
}
