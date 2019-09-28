package com.kasun.tasteit;

public class Petfood {

    private String foodCate;
    private String foodsubCate;
    private String foodPrice;
    private String expDate;
    private String foodBrand;

    public Petfood(){

    }

    public Petfood(String foodCate,String foodsubCate,String foodPrice,String expDate,String foodBrand){
        this.expDate = expDate;
        this.foodsubCate = foodsubCate;
        this.foodPrice = foodPrice;
        this.expDate = expDate;
        this.foodBrand = foodBrand;
    }

    public String getFoodCate() {
        return foodCate;
    }

    public void setFoodCate(String foodCate) {
        this.foodCate = foodCate;
    }

    public String getFoodsubCate() {
        return foodsubCate;
    }

    public void setFoodsubCate(String foodsubCate) {
        this.foodsubCate = foodsubCate;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getFoodBrand() {
        return foodBrand;
    }

    public void setFoodBrand(String foodBrand) {
        this.foodBrand = foodBrand;
    }







}
