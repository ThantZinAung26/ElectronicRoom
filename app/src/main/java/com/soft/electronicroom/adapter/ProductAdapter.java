package com.soft.electronicroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.electronicroom.R;
import com.soft.electronicroom.model.Product;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.HomeViewHolder> {

    private static final DiffUtil.ItemCallback<Product> DIFF_UTIL = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    private OnAdapterItemClickListener onAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    public ProductAdapter() {
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_home_view, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Product product = getItem(position);
        holder.itemName.setText(product.getName());
        holder.itemPrice.setText(String.valueOf(product.getPrice()));
        // holder.itemDescription.setText(product.getDescription());
        //holder.itemSubCategory.setText("Samsung");
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        final TextView itemName;
        final TextView itemPrice;
        //final TextView itemDescription;
        //final TextView itemSubCategory;
        //final float itemRate;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tvName);
            itemPrice = itemView.findViewById(R.id.tvPrice);
            //itemDescription = itemView.findViewById(R.id.ed_description);
            //itemRate = (Float) itemView.findViewById(R.id.ed_rate);
            //itemSubCategory = itemView.findViewById(R.id.spinnerSubCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAdapterItemClickListener != null) {
                        onAdapterItemClickListener.onClick(getItem(getAdapterPosition()));
                    }
                }
            });

        }
    }

    public interface OnAdapterItemClickListener {
        void onClick(Product product);
    }

}
