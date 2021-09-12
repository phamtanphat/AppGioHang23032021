package com.example.appgiohang23032021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgiohang23032021.adapter.CartAdapter;
import com.example.appgiohang23032021.constants.AppCache;
import com.example.appgiohang23032021.constants.CartSingleton;
import com.example.appgiohang23032021.interfaces.OnClickChangeAmount;
import com.example.appgiohang23032021.models.Product;
import com.example.appgiohang23032021.models.Response;

import java.text.DecimalFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    CartAdapter mCartAdapter;
    RecyclerView mRcvCart;
    TextView mTvTotalPrice;
    Button mBtnPayment;
    long mTotalPrice = 0;
    Response mResponse;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mRcvCart = findViewById(R.id.rv_product_list);
        mTvTotalPrice = findViewById(R.id.textTotalPrice);
        mBtnPayment = findViewById(R.id.buttonPayment);
        mToolbar = findViewById(R.id.toolbarCart);

        mToolbar.setNavigationIcon(R.drawable.ic_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mCartAdapter = new CartAdapter(CartSingleton.getInstance().getCart());

        if (AppCache.checkFileExists(CartActivity.this)) {
            mResponse = CartSingleton.getInstance().tranFormStringToResponseData(AppCache.readFile(CartActivity.this));
            List<Product> listProducts = mResponse.getProducts();
            if (listProducts != null && listProducts.size() > 0) {
                CartSingleton.getInstance().addAllCarts(listProducts);
            }
        }

        mRcvCart.setAdapter(mCartAdapter);
        priceTotal();
        mCartAdapter.setOnClickChangeAmount(new OnClickChangeAmount() {
            @Override
            public void onChangeAmount(long id, int count) {
                for (int i = 0; i < CartSingleton.getInstance().getCart().size(); i++) {
                    if (CartSingleton.getInstance().getCart().get(i).getId() == id) {
                        if (count <= 0) {
                            mCartAdapter.getList().remove(i);
                            CartSingleton.getInstance().getCart().remove(i);
                        } else {
                            mCartAdapter.getList().get(i).setCount(count);
                            CartSingleton.getInstance().getCart().get(i).setCount(count);
                        }
                    }
                }

                priceTotal();
                if (AppCache.checkFileExists(CartActivity.this)) {
                    mResponse.setProducts(CartSingleton.getInstance().getCart());
                    AppCache.updateFile(CartActivity.this, CartSingleton.getInstance().createJson(mResponse.getUser(), mResponse.getProducts()).toString());
                }
            }
        });

        mBtnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppCache.checkFileExists(CartActivity.this)) {
                    mResponse = CartSingleton.getInstance().tranFormStringToResponseData(AppCache.readFile(CartActivity.this));
                    if (mResponse.getProducts().size() <= 0){
                        Toast.makeText(CartActivity.this, "Giỏ hàng của bạn rỗng", Toast.LENGTH_SHORT).show();
                    }else{
                        CartSingleton.getInstance().clearCart();
                        mResponse.setProducts(CartSingleton.getInstance().getCart());
                        AppCache.updateFile(CartActivity.this, CartSingleton.getInstance().createJson(mResponse.getUser(), mResponse.getProducts()).toString());
                        finish();
                        Toast.makeText(CartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void priceTotal() {
        mTotalPrice = 0;
        for (Product product : CartSingleton.getInstance().getCart()) {
            mTotalPrice += CartSingleton.getInstance().priceProductSales((long) (product.getPrice() * product.getCount()), product.getSaleOff().getPercent());
        }
        mTvTotalPrice.setText(new DecimalFormat("#,###").format(mTotalPrice) + "");
    }
}