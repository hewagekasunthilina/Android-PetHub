package com.kasun.tasteit.Model;

public class Complain {

    private String id;
    private String name;
    private String email;
    private String complain;

    public Complain() {
    }

    public Complain(String id, String name, String email, String complain) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.complain = complain;
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

    public String getComplain() {
        return complain;
    }

}
