package com.soft.electronicroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.electronicroom.R;
import com.soft.electronicroom.model.MainCategory;

import java.util.List;

public class CategoryAdapter extends ListAdapter<MainCategory, CategoryAdapter.CategoryViewHolder> {

    private static final DiffUtil.ItemCallback<MainCategory> DIFF_UTIL = new DiffUtil.ItemCallback<MainCategory>() {
        @Override
        public boolean areItemsTheSame(@NonNull MainCategory oldItem, @NonNull MainCategory newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MainCategory oldItem, @NonNull MainCategory newItem) {
            return oldItem.equals(newItem);
        }
    };

    public CategoryAdapter() {
        super(DIFF_UTIL);
    }

    public interface OnAdapterItemClickListener {
        void onClick(MainCategory mainCategory);
    }

    private OnAdapterItemClickListener onAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_category_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        MainCategory mainCategory = getItem(position);
        holder.name.setText(mainCategory.getName());
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        final TextView name;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.categoryName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onAdapterItemClickListener)
                        onAdapterItemClickListener.onClick(getItem(getAdapterPosition()));
                }
            });

        }
    }

}
