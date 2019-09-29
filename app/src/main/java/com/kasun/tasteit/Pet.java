package com.kasun.tasteit;

public class Pet {

    private String Id;
    private String FamilyName;
    private String Model;
    private String Age;
    private String NickName;
    private String Gender;

    public Pet(){

    }

    public Pet(String id,String familyName, String model, String age, String nickName, String gender) {
        Id = id;
        FamilyName = familyName;
        Model = model;
        Age = age;
        NickName = nickName;
        Gender = gender;
    }

    public String getId(){
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        FamilyName = familyName;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
