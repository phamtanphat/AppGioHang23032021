package com.example.appgiohang23032021.models;

public class Product {
    private long id;
    private String name;
    private int image;
    private double price;
    private int idSaleOff;
    private int count;

    public Product(long id, String name, int image, double price, int idSaleOff, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.idSaleOff = idSaleOff;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdSaleOff() {
        return idSaleOff;
    }

    public void setIdSaleOff(int idSaleOff) {
        this.idSaleOff = idSaleOff;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
