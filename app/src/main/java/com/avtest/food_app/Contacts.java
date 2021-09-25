package com.avtest.food_app;

public class Contacts {

    private  String name;
    private String price;
    private String discount;
    private String Quantity;

    public Contacts( String name,String price,String discount,String Quantity) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.Quantity = Quantity;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getQuantity() {
        return Quantity;
    }
}
