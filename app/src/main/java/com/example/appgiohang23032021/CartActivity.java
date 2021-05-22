package com.example.appgiohang23032021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.appgiohang23032021.adapter.CartAdapter;
import com.example.appgiohang23032021.constants.AppCache;
import com.example.appgiohang23032021.constants.CartSingleton;
import com.example.appgiohang23032021.interfaces.OnClickChangeAmount;
import com.example.appgiohang23032021.models.Product;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {

    CartAdapter mCartAdapter;
    RecyclerView mRcvCart;
    TextView mTvTotalPrice;
    Button mBtnPayment;
    long mTotalPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mRcvCart = findViewById(R.id.rv_product_list);
        mTvTotalPrice = findViewById(R.id.textTotalPrice);
        mBtnPayment = findViewById(R.id.buttonPayment);

        mCartAdapter = new CartAdapter(CartSingleton.getInstance().getCart());

        mRcvCart.setAdapter(mCartAdapter);
        priceTotal();
        mCartAdapter.setOnClickChangeAmount(new OnClickChangeAmount() {
            @Override
            public void onChangeAmount(long id, int count) {
                for (int i = 0; i < CartSingleton.getInstance().getCart().size() ; i++) {
                    if(CartSingleton.getInstance().getCart().get(i).getId() == id){
                        if (count <= 0){
                            CartSingleton.getInstance().getCart().remove(i);
                            mCartAdapter.notifyItemRemoved(i);
                        }else{
                            CartSingleton.getInstance().getCart().get(i).setCount(count);
                            mCartAdapter.notifyItemChanged(i);
                        }
                    }
                }
                priceTotal();
                if (!AppCache.createFile(CartSingleton.getInstance().createJson(CartSingleton.getInstance().getCart()).toString(),CartActivity.this)){
                    AppCache.deleteFile(CartActivity.this);
                }
            }
        });
    }

    private void priceTotal(){
        mTotalPrice = 0;
        for (Product product : CartSingleton.getInstance().getCart()) {
            mTotalPrice += CartSingleton.getInstance().priceProductSales((long) (product.getPrice() * product.getCount()), product.getSaleOff().getPercent());
        }
        mTvTotalPrice.setText(new DecimalFormat("#,###").format(mTotalPrice) + "");
    }
}