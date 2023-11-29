package com.iwa.recruiterservice.establishment;

import com.iwa.recruiterservice.address.Address;
import com.iwa.recruiterservice.address.AddressService;
import com.iwa.recruiterservice.dto.AddressDTO;
import com.iwa.recruiterservice.dto.EstablishmentDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentService {

    private EstablishmentRepository establishmentRepository;
    private final AddressService addressService;

    public EstablishmentService(EstablishmentRepository establishmentRepository, AddressService addressService) {
        this.establishmentRepository = establishmentRepository;
        this.addressService = addressService;
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
    public Optional<Establishment> updateEstablishment(Long id, Establishment updatedEstablishment) {
        Optional<Establishment> existingEstablishment = establishmentRepository.findById(id);

        if (existingEstablishment != null) {
            updatedEstablishment.setId(id);
            return Optional.of(establishmentRepository.save(updatedEstablishment));
        } else {
            return null;
        }
    }
    
    public List<EstablishmentDTO> getEstablishmentsDTO(){
        List<Establishment> establishments = this.establishmentRepository.findAll();
        if (establishments.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<EstablishmentDTO> establishmentsDTO = new java.util.ArrayList<>();
            for (Establishment establishment : establishments) {
                establishmentsDTO.add(getEstablishmentDTO(establishment.getId()).get());
            }
            return establishmentsDTO;
        }
    }

    public Optional<EstablishmentDTO> getEstablishmentDTO(Long id){
        Optional<Establishment> establishment = this.establishmentRepository.findById(id);
        if (establishment.isPresent()) {
            Establishment existingEstablishment = establishment.get();
            Address address = addressService.getAddressById(existingEstablishment.getAddressId()).get();
            AddressDTO addressDTO = new AddressDTO(address.getId(), address.getStreetNum(), address.getStreet(), address.getComplement(), address.getCity(), address.getZipCode(), address.getCountry());
            return Optional.of(new EstablishmentDTO(existingEstablishment.getId(), existingEstablishment.getName(), existingEstablishment.getSiret(), addressDTO));
        } else {
            return Optional.empty();
        }
    }

    public void deleteAll() {
        establishmentRepository.deleteAll();
    }
}
