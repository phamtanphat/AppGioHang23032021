package com.example.appgiohang23032021.models;

import com.example.appgiohang23032021.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static List<Product> getDataMock(){
        return new ArrayList<>(Arrays.asList(
                new Product(1,"iPhone 12 Pro Max 256GB", R.drawable.hinhiphone12,37490000,1 , 0),
                new Product(2,"Xiaomi Mi 11 Lite", R.drawable.hinhxiaomi11,7990000,6 , 0),
                new Product(3,"Samsung Galaxy Note 20", R.drawable.hinhsamsunggalaxynote20,15990000,4 , 0),
                new Product(4,"Vivo V21 5G", R.drawable.hinhvivov21,9990000,5 , 0),
                new Product(5,"Realme 8 Pro", R.drawable.hinhrealme8,8690000,3 , 0),
                new Product(6,"Xiami Redme 9T", R.drawable.hinhxiaomiredme9t,4290000,3 , 0),
                new Product(7,"Oppo A74 5G", R.drawable.hinhoppoa74,7490000,2 , 0),
                new Product(8,"Samsung Galaxy A32", R.drawable.hinhsamsunggalaxya32,6490000,4 , 0)
        ));
    }
}
