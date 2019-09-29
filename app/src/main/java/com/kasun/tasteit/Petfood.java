package com.kasun.tasteit;

public class Petfood {

    private String foodName;
    private String foodBrand;
    private String foodPrice;
    private String foodexpDate;
    private String foodmanDate;



    private String foodId;

    public Petfood(String foodId, String foodName,String foodBrand,String foodPrice,String foodexpDate,String foodmanDate){
        this.foodName = foodName;
        this.foodBrand = foodBrand;
        this.foodPrice = foodPrice;
        this.foodexpDate = foodexpDate;
        this.foodmanDate = foodmanDate;
        this.foodId = foodId;
    }


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodBrand() {
        return foodBrand;
    }

    public void setFoodBrand(String foodBrand) {
        this.foodBrand = foodBrand;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodexpDate() {
        return foodexpDate;
    }

    public void setFoodexpDate(String foodexpDate) {
        this.foodexpDate = foodexpDate;
    }

    public String getFoodmanDate() {
        return foodmanDate;
    }

    public void setFoodmanDate(String foodmanDate) {
        this.foodmanDate = foodmanDate;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Petfood(){

    }






}
