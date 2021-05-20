package com.example.appgiohang23032021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appgiohang23032021.adapter.CartAdapter;
import com.example.appgiohang23032021.constants.CartSingleton;

public class CartActivity extends AppCompatActivity {

    CartAdapter mCartAdapter;
    RecyclerView mRcvCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mRcvCart = findViewById(R.id.rv_product_list);

        mCartAdapter = new CartAdapter(CartSingleton.getInstance().getCart());

        mRcvCart.setAdapter(mCartAdapter);
    }
}