package com.iwa.reviewservice.dto;

import java.io.Serializable;

public class ReferenceDTO implements Serializable {
    private String refName;
    private String refEstablishment;
    private AddressDTO refAddress;
    private String refPhone;
    private String refEmail;

    public ReferenceDTO() {
    }

    public ReferenceDTO(String refName, String refEstablishment, AddressDTO refAddress, String refPhone, String refEmail) {
        this.refName = refName;
        this.refEstablishment = refEstablishment;
        this.refAddress = refAddress;
        this.refPhone = refPhone;
        this.refEmail = refEmail;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getRefEstablishment() {
        return refEstablishment;
    }

    public void setRefEstablishment(String refEstablishment) {
        this.refEstablishment = refEstablishment;
    }

    public AddressDTO getRefAddress() {
        return refAddress;
    }

    public void setRefAddress(AddressDTO refAddress) {
        this.refAddress = refAddress;
    }

    public String getRefPhone() {
        return refPhone;
    }

    public void setRefPhone(String refPhone) {
        this.refPhone = refPhone;
    }

    public String getRefEmail() {
        return refEmail;
    }

    public void setRefEmail(String refEmail) {
        this.refEmail = refEmail;
    }
}