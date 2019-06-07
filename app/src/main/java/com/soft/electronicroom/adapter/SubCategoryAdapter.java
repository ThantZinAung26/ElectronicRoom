package com.soft.electronicroom.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.electronicroom.R;
import com.soft.electronicroom.model.SubCategory;

public class SubCategoryAdapter extends ListAdapter<SubCategory, SubCategoryAdapter.ProductViewHolder> {

    private static final DiffUtil.ItemCallback<SubCategory> DIFF_UTIL = new DiffUtil.ItemCallback<SubCategory>() {
        @Override
        public boolean areItemsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
            return newItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
            return oldItem.equals(newItem);
        }
    };

    public SubCategoryAdapter() {
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_product_view, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        SubCategory subCategory = getItem(position);
        holder.editTitle.setText(subCategory.getName());
        Log.d("ID","id _" + subCategory.getId());
//        holder.editCategory.setText(subCategory.getMainCategory().getName());
    }

    public interface OnAdapterItemClickListener {
        void onClick(SubCategory subCategory);
    }

    private OnAdapterItemClickListener onAdapterItemClickListener;

    class ProductViewHolder extends RecyclerView.ViewHolder {

        final TextView editTitle;
//        final TextView editCategory;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            editTitle = itemView.findViewById(R.id.productTv);
//            editCategory = itemView.findViewById(R.id.categoryTv);

            itemView.setOnClickListener(v -> {
                if (onAdapterItemClickListener != null) {
                    onAdapterItemClickListener.onClick(getItem(getAdapterPosition()));
                }
            });
        }
    }

    public void setOnItemClickListener(OnAdapterItemClickListener onItemClickListener) {
        this.onAdapterItemClickListener = onItemClickListener;
    }
}
