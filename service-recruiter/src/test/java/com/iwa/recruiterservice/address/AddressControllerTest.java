package com.iwa.recruiterservice.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressService addressService;

    private Address address;

    @Test
    void getAllAddresses() throws Exception {
        when(addressService.getAddresses()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(addressService, times(1)).getAddresses();
    }

    @Test
    void getAddressById() throws Exception {
        long addressId = 1L;
        Address address = new Address(addressId, "123", "Main St", null, "City", "12345", "Country");

        when(addressService.getAddressById(addressId)).thenReturn(Optional.of(address));

        mockMvc.perform(get("/api/addresses/{id}", addressId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addressId))
                .andExpect(jsonPath("$.streetNum").value("123"))
                .andExpect(jsonPath("$.street").value("Main St"))
                .andExpect(jsonPath("$.city").value("City"))
                .andExpect(jsonPath("$.zipCode").value("12345"))
                .andExpect(jsonPath("$.country").value("Country"));

        verify(addressService, times(1)).getAddressById(addressId);
    }

    @Test
    void createAddress() throws Exception {
        Address newAddress = new Address(null, "456", "Oak St", null, "City", "67890", "Country");

        when(addressService.createAddress(any())).thenReturn(new Address(1L, "456", "Oak St", null, "City", "67890", "Country"));

        mockMvc.perform(post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAddress)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.streetNum").value("456"))
                .andExpect(jsonPath("$.street").value("Oak St"))
                .andExpect(jsonPath("$.zipCode").value("67890"))
                .andExpect(jsonPath("$.country").value("Country"));

        verify(addressService, times(1)).createAddress(any());
    }

    @Test
    void updateAddress() throws Exception {
        long addressId = 1L;
        Address updatedAddress = new Address(addressId, "789", "Pine St", null, "City", "55555", "UpdatedCountry");

        when(addressService.updateAddress(eq(addressId), any())).thenReturn(Optional.of(updatedAddress));

        mockMvc.perform(put("/api/addresses/{id}", addressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAddress)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addressId))
                .andExpect(jsonPath("$.streetNum").value("789"))
                .andExpect(jsonPath("$.street").value("Pine St"))
                .andExpect(jsonPath("$.zipCode").value("55555"))
                .andExpect(jsonPath("$.country").value("UpdatedCountry"));

        verify(addressService, times(1)).updateAddress(eq(addressId), any());
    }

    @Test
    void updateAddressNotFound() throws Exception {
        long nonExistingId = 99L;

        when(addressService.updateAddress(eq(nonExistingId), any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/addresses/{id}", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Address())))
                .andExpect(status().isNotFound());

        verify(addressService, times(1)).updateAddress(eq(nonExistingId), any());
    }

    @Test
    void deleteAddress() throws Exception {
        long addressId = 1L;

        when(addressService.deleteAddressById(addressId)).thenReturn(true);

        mockMvc.perform(delete("/api/addresses/{id}", addressId))
                .andExpect(status().isOk())
                .andExpect(content().string("Address deleted successfully"));

        verify(addressService, times(1)).deleteAddressById(addressId);
    }

    @Test
    void deleteAddressNotFound() throws Exception {
        long nonExistingId = 99L;

        when(addressService.deleteAddressById(nonExistingId)).thenReturn(false);

        mockMvc.perform(delete("/api/addresses/{id}", nonExistingId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Address not found"));

        verify(addressService, times(1)).deleteAddressById(nonExistingId);
    }
}
