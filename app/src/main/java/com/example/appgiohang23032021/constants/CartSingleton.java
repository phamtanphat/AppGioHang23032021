package com.example.appgiohang23032021.constants;

import com.example.appgiohang23032021.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private List<Product> mList;
    private static CartSingleton mInstance = null;

    private CartSingleton() {
        mList = new ArrayList<>();
    }

    public static CartSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new CartSingleton();
        }
        return mInstance;
    }

    public void pushProduct(Product product){
        boolean instance = false;
        if (mList.size() == 0 ){
            mList.add(product);
        }else{
            for (int i = 0 ; i < mList.size() ; i++){
                if (product.getId() == mList.get(i).getId()){
                    mList.get(i).setCount(mList.get(i).getCount() + 1);
                    instance = true;
                    break;
                }else{
                    instance = false;
                }
            }
            if (!instance){
                mList.add(product);
            }
        }
    }

    public void clearCart(){
        mList.clear();
    }

    public List<Product> getCart(){
        return mList;
    }

    public long priceProductSales(long price , double percent){
        return (long) (price * ((100 - percent) / 100));
    }


}
