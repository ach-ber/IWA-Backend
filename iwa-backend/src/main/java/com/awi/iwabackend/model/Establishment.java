package com.awi.iwabackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "establishments")
public class Establishment {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer siret;
    private byte[] documents;
    private Integer address_key;

    public Establishment() {
    }

    public Establishment(Integer id, String name, Integer siret, byte[] documents, Integer address_key) {
        this.id = id;
        this.name = name;
        this.siret = siret;
        this.documents = documents;
        this.address_key = address_key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSiret() {
        return siret;
    }

    public void setSiret(Integer siret) {
        this.siret = siret;
    }

    public byte[] getDocuments() {
        return documents;
    }

    public void setDocuments(byte[] documents) {
        this.documents = documents;
    }

    public Integer getAddress_key() {
        return address_key;
    }

    public void setAddress_key(Integer address_key) {
        this.address_key = address_key;
    }
}
