package com.soft.electronicroom.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.electronicroom.R;
import com.soft.electronicroom.model.Product;

public class HomeAdapter extends ListAdapter<Product, HomeAdapter.HomeViewHolder> {

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

    public HomeAdapter(){
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        final TextView itemName;
        final TextView itemPrice;
        final TextView itemDescription;
        //final float itemRate;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ed_name);
            itemPrice = itemView.findViewById(R.id.ed_price);
            itemDescription = itemView.findViewById(R.id.ed_description);
            //itemRate = (Float) itemView.findViewById(R.id.ed_rate);

        }
    }

    public interface OnAdapterItemClickListener{
        void onClick(Product product);
    }

}
