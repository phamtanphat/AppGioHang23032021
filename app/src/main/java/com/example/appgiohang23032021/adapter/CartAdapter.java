package com.example.appgiohang23032021.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.appgiohang23032021.R;
import com.example.appgiohang23032021.models.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> mListProduct;
    private NumberFormat mNumberFormat;

    public CartAdapter(List<Product> products) {
        this.mListProduct = products;
        mNumberFormat = new DecimalFormat("#,###");
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product currentProduct = mListProduct.get(position);
        if (currentProduct == null) {
            return;
        }
        holder.imgCart.setImageResource(currentProduct.getImage());
        holder.tvName.setText(currentProduct.getName());
        holder.tvPrice.setText(mNumberFormat.format(currentProduct.getPrice()) + " " + Html.fromHtml("<u>đ</u>"));
        holder.tvAmount.setText(currentProduct.getCount()+"");

    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCart,imgInCrease,imgDeCrease;
        private TextView tvName,tvPrice,tvAmount,tvPriceSale;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.img_cart);
            tvName = itemView.findViewById(R.id.tv_name_cart);
            tvPrice = itemView.findViewById(R.id.tv_price_cart);
            tvAmount = itemView.findViewById(R.id.editTextAmount);
            imgInCrease = itemView.findViewById(R.id.imageViewInCrease);
            imgDeCrease = itemView.findViewById(R.id.imageViewDeCrease);
            swipeRevealLayout = itemView.findViewById(R.id.swipeLayout);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);
            tvPriceSale = itemView.findViewById(R.id.tv_price_sale);
        }
    }

}
