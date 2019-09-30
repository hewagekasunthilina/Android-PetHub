package com.kasun.tasteit;

public class Petfood {

    private String foodName;
    private String foodBrand;
    private String foodPrice;
    private String foodExpDate;
    private String foodManDate;
    private String imageUrl;

    public Petfood(){

    }

    public Petfood(String foodName, String foodBrand, String foodPrice, String foodExpDate, String foodManDate, String imageUrl) {
        this.foodName = foodName;
        this.foodBrand = foodBrand;
        this.foodPrice = foodPrice;
        this.foodExpDate = foodExpDate;
        this.foodManDate = foodManDate;
        this.imageUrl = imageUrl;
    }


    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodBrand(String foodBrand) {
        this.foodBrand = foodBrand;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodExpDate(String foodExpDate) {
        this.foodExpDate = foodExpDate;
    }

    public void setFoodManDate(String foodManDate) {
        this.foodManDate = foodManDate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodBrand() {
        return foodBrand;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public String getFoodExpDate() {
        return foodExpDate;
    }

    public String getFoodManDate() {
        return foodManDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}