package com.example.appgiohang23032021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.os.Bundle;
import android.view.Menu;

import com.example.appgiohang23032021.adapter.ProductAdapter;
import com.example.appgiohang23032021.models.Product;
import com.example.appgiohang23032021.models.SaleOff;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    List<Product> mListProduct;
    RecyclerView mRcvProduct;
    ProductAdapter mProductAdapter;
    Toolbar mToolbar;
    SearchView mSearchView;
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
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }
}