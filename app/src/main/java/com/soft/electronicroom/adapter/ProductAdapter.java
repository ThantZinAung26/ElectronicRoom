package com.soft.electronicroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.soft.electronicroom.R;
import com.soft.electronicroom.listener.OnAdapterItemClickListener;
import com.soft.electronicroom.model.Product;
import com.soft.electronicroom.model.SubCategory;

public class ProductAdapter extends ListAdapter<SubCategory, ProductAdapter.ProductViewHolder> {

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

    public ProductAdapter() {
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        SubCategory subCategory = getItem(position);
        holder.editTitle.setText(subCategory.getName());
        holder.editReleaseDate.setText((CharSequence) subCategory.getRelease());
        holder.editCategory.setText(subCategory.getRelease().toString());
    }

    public interface OnAdapterItemClickListener {
        void onClick(SubCategory subCategory);
    }

    private OnAdapterItemClickListener onAdapterItemClickListener;

    class ProductViewHolder extends RecyclerView.ViewHolder {

        final TextInputEditText editTitle;
        final TextInputEditText editReleaseDate;
        final TextInputEditText editCategory;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            editTitle = itemView.findViewById(R.id.edtitle);
            editReleaseDate = itemView.findViewById(R.id.edreleaseDate);
            editCategory = itemView.findViewById(R.id.edCategory);

            if (onAdapterItemClickListener != null) {
                onAdapterItemClickListener.onClick(getItem(getAdapterPosition()));
            }
        }
    }

    public void setOnItemClickListener(OnAdapterItemClickListener onItemClickListener) {
        this.onAdapterItemClickListener = onItemClickListener;
    }
}
