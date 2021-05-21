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
import com.example.appgiohang23032021.interfaces.OnClickChangeAmount;
import com.example.appgiohang23032021.models.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> mListProduct;
    private NumberFormat mNumberFormat;
    private ViewBinderHelper mViewBinderHelper;
    private OnClickChangeAmount mOnClickChangeAmount;

    public CartAdapter(List<Product> products) {
        this.mListProduct = products;
        mNumberFormat = new DecimalFormat("#,###");
        mViewBinderHelper = new ViewBinderHelper();
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
        mViewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(currentProduct.getId()));
        holder.imgCart.setImageResource(currentProduct.getImage());
        holder.tvName.setText(currentProduct.getName());
        holder.tvPrice.setText(Html.fromHtml( mNumberFormat.format(currentProduct.getPrice()) + " " + "<u>đ</u>"));
        String priceSale = mNumberFormat.format(currentProduct.getPrice() * ((100 - currentProduct.getSaleOff().getPercent()) / 100 ));
        String percent = currentProduct.getSaleOff().getPercent() + "%";
        holder.tvPriceSale.setText(Html.fromHtml("<del>"+priceSale+"</del>" + "   " + percent.replace(".0","")) );
        holder.tvAmount.setText(currentProduct.getCount()+"");

        holder.imgInCrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickChangeAmount != null){
                    mOnClickChangeAmount.onChangeAmount(currentProduct.getId(),currentProduct.getCount() + 1);
                }
            }
        });

        holder.imgDeCrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickChangeAmount != null){
                    mOnClickChangeAmount.onChangeAmount(currentProduct.getId(),currentProduct.getCount() - 1);
                }
            }
        });
        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickChangeAmount != null){
                    mOnClickChangeAmount.onChangeAmount(currentProduct.getId(),0);
                }
            }
        });
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

    public void setOnClickChangeAmount(OnClickChangeAmount onClickChangeAmount){
        this.mOnClickChangeAmount = onClickChangeAmount;
    }
}
