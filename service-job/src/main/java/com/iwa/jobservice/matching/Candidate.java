package com.iwa.jobservice.matching;

import java.util.List;

public class Candidate implements Comparable {
    private String id;

    private String name;

    private String email;
    private String phone;
    private String photo;

    private List<ExperienceDTO> experiences;

    private List<AvailabilityDTO> availabilities;

    private List<OpinionDTO> opinions;

    private double score = 0;

    public Candidate() {
    }

    public Candidate(CandidateDTO candidateDTO, double score) {
        this.id = candidateDTO.getId();
        this.name = candidateDTO.getFirstname() + " " + candidateDTO.getLastname();
        this.email = candidateDTO.getEmail();
        this.phone = candidateDTO.getPhone();
        this.photo = candidateDTO.getPhoto();
        this.experiences = candidateDTO.getExperiences();
        this.availabilities = candidateDTO.getAvailabilities();
        this.opinions = candidateDTO.getOpinions();
        this.score = score;
    }

    public Candidate(String id, String name, List<ExperienceDTO> experiences, List<AvailabilityDTO> availabilities, List<OpinionDTO> opinions) {
        this.id = id;
        this.name = name;
        this.experiences = experiences;
        this.availabilities = availabilities;
        this.opinions = opinions;
    }

    public Candidate(String name, List<ExperienceDTO> experiences, List<AvailabilityDTO> availabilities, List<OpinionDTO> opinions) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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

    @Override
    public int compareTo(Object o) {
        Candidate candidate = (Candidate) o;
        return this.getScore() > candidate.getScore() ? 1 : -1;
    }
}
