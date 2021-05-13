package com.example.appgiohang23032021.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SaleOff {
    private int id;
    private String title;
    private double percent;

    public SaleOff(int id, String title, double percent) {
        this.id = id;
        this.title = title;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public static List<SaleOff> getDataMock(){
        return new ArrayList<>(Arrays.asList(
                new SaleOff(1 , "Sale cho điện thoai iphone" , 5),
                new SaleOff(2 , "Sale cho điện thoai oppo" , 0),
                new SaleOff(3 , "Sale cho điện thoai realme" , 2),
                new SaleOff(4 , "Sale cho điện thoai samsung" , 6),
                new SaleOff(5 , "Sale cho điện thoai vivo" , 3),
                new SaleOff(6 , "Sale cho điện thoai xiaomi" , 1)
        ));
    }
}
