package com.example.appgiohang23032021.constants;

import com.example.appgiohang23032021.models.Product;
import com.example.appgiohang23032021.models.SaleOff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public void addAllCarts(List<Product> products){
        this.mList = products;
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

    public JSONArray createJson(List<Product> products){
        JSONArray jsonArray = new JSONArray();
        if (products.size() == 0 ){
            return jsonArray;
        }
        for (int i = 0; i < products.size(); i++) {
            try {
                JSONObject jsonObjectSale = new JSONObject();
                jsonObjectSale.put("id", products.get(i).getSaleOff().getId());
                jsonObjectSale.put("title", products.get(i).getSaleOff().getTitle());
                jsonObjectSale.put("percent", products.get(i).getSaleOff().getPercent());

                JSONObject jsonObjectProduct = new JSONObject();
                jsonObjectProduct.put("id", products.get(i).getId());
                jsonObjectProduct.put("name", products.get(i).getName());
                jsonObjectProduct.put("price", products.get(i).getPrice());
                jsonObjectProduct.put("saleOff", jsonObjectSale);
                jsonObjectProduct.put("count", products.get(i).getCount());
                jsonObjectProduct.put("image", products.get(i).getImage());

                jsonArray.put(jsonObjectProduct);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public List<Product> tranFormStringToListProduct(String data){
        List<Product> products = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectProduct = (JSONObject) jsonArray.get(i);
                long id = jsonObjectProduct.getLong("id");
                String name = jsonObjectProduct.getString("name");
                double price = jsonObjectProduct.getDouble("price");
                int count = jsonObjectProduct.getInt("count");
                int image = jsonObjectProduct.getInt("image");

                JSONObject jsonObjectSale = jsonObjectProduct.getJSONObject("saleOff");
                int idSale = jsonObjectSale.getInt("id");
                String title = jsonObjectSale.getString("title");
                double percent = jsonObjectSale.getDouble("percent");

                SaleOff saleOff = new SaleOff(idSale,title,percent);
                Product product = new Product(id,name,image,price,saleOff,count);

                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }

}
