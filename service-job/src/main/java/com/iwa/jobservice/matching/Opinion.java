package com.iwa.jobservice.matching;

public class Opinion implements Evaluation {
    private Long id;

    private int score;

    private String message;

    private Long employeeId;

    private Long providedAt;

    public Opinion() {
    }

    public Opinion(Long id, int score, String message, Long employeeId, Long providedAt) {
        this.id = id;
        this.score = score;
        this.message = message;
        this.employeeId = employeeId;
        this.providedAt = providedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProvidedAt() {
        return providedAt;
    }

    public void setProvidedAt(Long providedAt) {
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
