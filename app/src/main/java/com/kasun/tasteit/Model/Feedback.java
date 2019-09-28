package com.kasun.tasteit.Model;

public class Feedback {

    private String id;
    private String name;
    private String email;
    private String suggession;
    private String dateandtime;
    private Double ratingCount;

    public Feedback() {
    }

    public Feedback(String id, String name, String email, String suggession, String dateandtime, Double ratingCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.suggession = suggession;
        this.dateandtime = dateandtime;
        this.ratingCount = ratingCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSuggession() {
        return suggession;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public Double getRatingCount() {
        return ratingCount;
    }
}
