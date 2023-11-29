package com.iwa.recruiterservice.dto;

import java.io.Serializable;

public class EstablishmentDTO  implements Serializable {
    private Long id;
    private String name;
    private Long siret;
    private AddressDTO addressDTO;

    public EstablishmentDTO() {
    }

    public EstablishmentDTO(Long id, String name, Long siret, AddressDTO addressDTO) {
        this.id = id;
        this.name = name;
        this.siret = siret;
        this.addressDTO = addressDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSiret() {
        return siret;
    }

    public void setSiret(Long siret) {
        this.siret = siret;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }
}
