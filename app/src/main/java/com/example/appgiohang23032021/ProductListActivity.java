package com.example.appgiohang23032021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.appgiohang23032021.adapter.ProductAdapter;
import com.example.appgiohang23032021.constants.CartSingleton;
import com.example.appgiohang23032021.interfaces.OnItemClickProduct;
import com.example.appgiohang23032021.models.Product;
import com.example.appgiohang23032021.models.SaleOff;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    List<Product> mListProduct;
    RecyclerView mRcvProduct;
    ProductAdapter mProductAdapter;
    Toolbar mToolbar;
    SearchView mSearchView;
    TextView mTvBadgeCart;
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

        // event

        mProductAdapter.setOnItemClickProduct(new OnItemClickProduct() {
            @Override
            public void onClick(int index) {
                CartSingleton.getInstance().pushProduct(mListProduct.get(index));
                setupBadge();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product,menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //Hiển thị searchview full chiêu ngang của toolbar
//        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProductAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mProductAdapter.getFilter().filter(newText);
                return true;
            }
        });
        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();
        mTvBadgeCart = actionView.findViewById(R.id.textCartbage);
        setupBadge();

        return true;
    }

    public void setupBadge(){
        if (CartSingleton.getInstance().getCart().size() == 0){
            if (mTvBadgeCart != null){
                mTvBadgeCart.setVisibility(View.GONE);
            }
        }else{
            if (mTvBadgeCart != null){
                mTvBadgeCart.setVisibility(View.VISIBLE);
                mTvBadgeCart.setText(CartSingleton.getInstance().getCart().size()+"");
            }

        }
    }
}