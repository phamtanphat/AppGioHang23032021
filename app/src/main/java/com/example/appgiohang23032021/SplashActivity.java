package com.example.appgiohang23032021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appgiohang23032021.constants.AppCache;
import com.example.appgiohang23032021.constants.CartSingleton;
import com.example.appgiohang23032021.models.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppCache.checkFileExists(SplashActivity.this)){
                    Response response = CartSingleton.getInstance().tranFormStringToResponseData(AppCache.readFile(SplashActivity.this));
                    Intent intent;
                    if (response.getUser() != null && response.getUser().isLogin()){
                        intent = new Intent(SplashActivity.this, ProductListActivity.class);
                    }else{
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        },4000);

    }
}