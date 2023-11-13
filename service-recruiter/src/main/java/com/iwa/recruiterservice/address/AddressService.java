package com.iwa.recruiterservice.address;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Address createAddress(Address address){
        return this.addressRepository.save(address);
    }

    public Long getNumberOfAddresses(){
        return this.addressRepository.count();
    }

    public List<Address> getAddresses(){
        return this.addressRepository.findAll();
    }

    public Address getAddressById(Long id){
        return this.addressRepository.findById(id).orElse(null);
    }

    public void deleteAddressById(Long id){
        this.addressRepository.deleteById(id);
    }
}
