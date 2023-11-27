package com.iwa.recruiterservice.address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AddressServiceTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        address = new Address("1234", "1234", "1234", "1234", "1234", "1234");
    }

    @Test
    void createAddress() {
        assertTrue(addressRepository.findAll().isEmpty());
        Address result = addressService.createAddress(address);
        assertNotNull(result);
        assertEquals(address.getStreetNum(), result.getStreetNum());
        assertEquals(address.getStreet(), result.getStreet());
        assertEquals(address.getComplement(), result.getComplement());
        assertEquals(address.getCity(), result.getCity());
        assertEquals(address.getZipCode(), result.getZipCode());
        assertEquals(address.getCountry(), result.getCountry());
    }

    @Test
    void getAddressById() {
        assertTrue(addressRepository.findAll().isEmpty());
        Address createdAddress = addressService.createAddress(address);
        Address result = addressService.getAddressById(createdAddress.getId()).get();
        assertEquals(address.getStreetNum(), result.getStreetNum());
        assertEquals(address.getStreet(), result.getStreet());
        assertEquals(address.getComplement(), result.getComplement());
        assertEquals(address.getCity(), result.getCity());
        assertEquals(address.getZipCode(), result.getZipCode());
        assertEquals(address.getCountry(), result.getCountry());
    }

    @Test
    void deleteAddressById() {
        assertTrue(addressRepository.findAll().isEmpty());
        Address createdAddress = addressService.createAddress(address);
        assertTrue(addressRepository.existsById(createdAddress.getId()));
        addressService.deleteAddressById(createdAddress.getId());
        assertFalse(addressRepository.existsById(createdAddress.getId()));
    }

    @Test
    void updateAddress() {
        assertTrue(addressRepository.findAll().isEmpty());
        Address createdAddress = addressService.createAddress(address);
        Address updatedAddress = new Address("1111", "1111", "1111", "1111", "1111", "1111");
        Optional<Address> result = addressService.updateAddress(createdAddress.getId(), updatedAddress);
        assertNotNull(result);
        assertEquals(updatedAddress.getStreetNum(), result.get().getStreetNum());
        assertEquals(updatedAddress.getStreet(), result.get().getStreet());
        assertEquals(updatedAddress.getComplement(), result.get().getComplement());
        assertEquals(updatedAddress.getCity(), result.get().getCity());
        assertEquals(updatedAddress.getZipCode(), result.get().getZipCode());
        assertEquals(updatedAddress.getCountry(), result.get().getCountry());
    }
}