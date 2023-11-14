package com.iwa.recruiterservice.establishment;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Establishment> getEstablishmentById(Long id){
        return this.establishmentRepository.findById(id);
    }

    @Transactional
    public boolean deleteEstablishmentById(Long id){
        if (establishmentRepository.existsById(id)) {
            establishmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Establishment updateEstablishment(Long id, Establishment updatedEstablishment) {
        Establishment existingEstablishment = establishmentRepository.findById(id).orElse(null);

        if (existingEstablishment != null) {
            updatedEstablishment.setId(id);
            return establishmentRepository.save(updatedEstablishment);
        } else {
            return null;
        }
    }
}
