package com.iwa.recruiterservice.dto;

import java.io.Serializable;
import java.util.List;

public class CompanyDTO implements Serializable {
    private Long id;
    private String name;
    private Long siren;
    private List<EstablishmentDTO> establishments;

    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String name, Long siren, List<EstablishmentDTO> establishments) {
        this.id = id;
        this.name = name;
        this.siren = siren;
        this.establishments = establishments;
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

    public Long getSiren() {
        return siren;
    }

    public void setSiren(Long siren) {
        this.siren = siren;
    }

    public List<EstablishmentDTO> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<EstablishmentDTO> establishments) {
        this.establishments = establishments;
    }
}
