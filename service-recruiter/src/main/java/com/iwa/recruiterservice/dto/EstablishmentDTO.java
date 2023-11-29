package com.iwa.recruiterservice.dto;

import java.io.Serializable;

public class EstablishmentDTO  implements Serializable {
    private Long id;
    private String name;
    private Long siret;
    private Long addressId;

    public EstablishmentDTO() {
    }

    public EstablishmentDTO(Long id, String name, Long siret, Long addressId) {
        this.id = id;
        this.name = name;
        this.siret = siret;
        this.addressId = addressId;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
