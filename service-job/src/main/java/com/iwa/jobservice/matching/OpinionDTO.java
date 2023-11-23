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