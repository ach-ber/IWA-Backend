package com.iwa.jobservice.matching;

import java.io.Serializable;

public class EstablishmentDTO implements Serializable {
    private String establishmentName;
    private AddressDTO establishmentAddress;

    public EstablishmentDTO() {
    }

    public EstablishmentDTO(String establishmentName, AddressDTO establishmentAddress) {
        this.establishmentName = establishmentName;
        this.establishmentAddress = establishmentAddress;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public AddressDTO getEstablishmentAddress() {
        return establishmentAddress;
    }

    public void setEstablishmentAddress(AddressDTO establishmentAddress) {
        this.establishmentAddress = establishmentAddress;
    }
}