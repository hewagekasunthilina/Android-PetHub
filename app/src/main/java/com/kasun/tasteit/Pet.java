package com.kasun.tasteit;

public class Pet {

    private String familyName;
    private String model;
    private String age;
    private String nickName;
    private String gender;
    private String imageUrl;

    public Pet(){

    }

    public Pet(String familyName, String model, String age, String nickName, String gender, String imageUrl) {
        this.familyName = familyName;
        this.model = model;
        this.age = age;
        this.nickName = nickName;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }


    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getModel() {
        return model;
    }

    public String getAge() {
        return age;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGender() {
        return gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
