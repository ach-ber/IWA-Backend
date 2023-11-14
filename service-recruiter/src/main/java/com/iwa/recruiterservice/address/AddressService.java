package com.iwa.recruiterservice.address;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Address> getAddressById(Long id){
        return this.addressRepository.findById(id);
    }

    @Transactional
    public boolean deleteAddressById(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Address updateAddress(Long id, Address updatedAddress) {
        Address existingAddress = addressRepository.findById(id).orElse(null);

        if (existingAddress != null) {
            updatedAddress.setId(id);
            return addressRepository.save(updatedAddress);
        } else {
            return null;
        }
    }
}
