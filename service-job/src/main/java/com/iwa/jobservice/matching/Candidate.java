package com.iwa.jobservice.matching;

import java.util.List;

public class Candidate implements Comparable {
    private Long id;

    private String name;

    private List<Experience> experiences;

    private List<Availability> availabilities;

    private List<Opinion> opinions;

    private double score = 0;

    public Candidate() {
    }

    public Candidate(Long id, String name, List<Experience> experiences, List<Availability> availabilities, List<Opinion> opinions) {
        this.id = id;
        this.name = name;
        this.experiences = experiences;
        this.availabilities = availabilities;
        this.opinions = opinions;
    }

    public Candidate(String name, List<Experience> experiences, List<Availability> availabilities, List<Opinion> opinions) {
        this.name = name;
        this.experiences = experiences;
        this.availabilities = availabilities;
        this.opinions = opinions;
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

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        Candidate candidate = (Candidate) o;
        return this.getScore() > candidate.getScore() ? 1 : -1;
    }
}
