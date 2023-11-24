package com.iwa.jobservice.matching;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OpinionDTO implements Evaluation, Serializable {
    private int score;
    private String message;
    private String employerId;
    private LocalDateTime providedAt;

    public OpinionDTO() {
    }

    public OpinionDTO(int score, String message, String employerId, LocalDateTime providedAt) {
        this.score = score;
        this.message = message;
        this.employerId = employerId;
        this.providedAt = providedAt;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public LocalDateTime getProvidedAt() {
        return providedAt;
    }

    public void setProvidedAt(LocalDateTime providedAt) {
        this.providedAt = providedAt;
    }

    // points calculés en fonction de la note donnée par l'employeur
    // x représente une review avec une note à 5 étoiles
    // y représente une review avec une note à 4 étoiles
    // z représente une review avec une note à 3 étoiles
    // -z représente une review avec une note à 2 étoiles
    // -y représente une review avec une note à 1 étoile
    // -x représente une review avec une note à 0 étoile
    // on suppose que
    // 1 review à 5 étoiles = 2 reviews à 4 étoiles = 6 reviews à 3 étoiles
    // ainsi, on a
    // 5x - 8y = 0
    // 4y - 6z = 0
    // x = 32/5, y = 4, z = 8/3
    @Override
    public double getValue() {
        switch (score) {
            case 0:
                return -32/5;
            case 1:
                return -4;
            case 2:
                return -8/3;
            case 3:
                return 8/3;
            case 4:
                return 4;
            case 5:
                return 32/5;
            default:
                return 0;
        }
    }
}