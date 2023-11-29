package com.iwa.reviewservice.dto;

import java.util.List;

public class CandidateDTO {
    private String id;
    private String firstname;
    private String lastname;
    private int gender;
    private String birthDate;
    private String citizenship;
    private AddressDTO address;
    private String email;
    private String phone;
    private String photo;
    private String cv;
    private String shortBio;
    private List<ReferenceDTO> references;
    private List<ExperienceDTO> experiences;
    private List<AvailabilityDTO> availabilities;
    private List<OpinionDTO> opinions;

    public CandidateDTO() {
    }

    public CandidateDTO(String id, String firstname, String lastname, int gender, String birthDate, String citizenship, AddressDTO address, String email, String phone, String photo, String cv, String shortBio, List<ReferenceDTO> references, List<ExperienceDTO> experiences, List<AvailabilityDTO> availabilities, List<OpinionDTO> opinions) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.citizenship = citizenship;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.cv = cv;
        this.shortBio = shortBio;
        this.references = references;
        this.experiences = experiences;
        this.availabilities = availabilities;
        this.opinions = opinions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public List<ReferenceDTO> getReferences() {
        return references;
    }

    public void setReferences(List<ReferenceDTO> references) {
        this.references = references;
    }

    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public List<AvailabilityDTO> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityDTO> availabilities) {
        this.availabilities = availabilities;
    }

    public List<OpinionDTO> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<OpinionDTO> opinions) {
        this.opinions = opinions;
    }

    @Override
    public String toString() {
        return "CandidateDTO{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", birthDate='" + birthDate + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", cv='" + cv + '\'' +
                ", shortBio='" + shortBio + '\'' +
                ", references=" + references +
                ", experiences=" + experiences +
                ", availabilities=" + availabilities +
                ", opinions=" + opinions +
                '}';
    }
}
