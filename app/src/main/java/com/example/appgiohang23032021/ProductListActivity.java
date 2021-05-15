package com.example.appgiohang23032021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appgiohang23032021.adapter.ProductAdapter;
import com.example.appgiohang23032021.models.Product;
import com.example.appgiohang23032021.models.SaleOff;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    List<Product> mListProduct;
    RecyclerView mRcvProduct;
    ProductAdapter mProductAdapter;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mRcvProduct = findViewById(R.id.recyclerViewProduct);
        mToolbar = findViewById(R.id.toolbarProduct);

        setSupportActionBar(mToolbar);

        mListProduct = Product.getDataMock();


        mProductAdapter = new ProductAdapter(mListProduct);

        mRcvProduct.setHasFixedSize(true);
        mRcvProduct.setAdapter(mProductAdapter);
    }
}