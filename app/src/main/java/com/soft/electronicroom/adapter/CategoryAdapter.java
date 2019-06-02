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
import com.soft.electronicroom.listener.OnAdapterItemClickListener;
import com.soft.electronicroom.model.MainCategory;

public class CategoryAdapter extends ListAdapter<MainCategory, CategoryAdapter.CategoryViewHolder> implements OnAdapterItemClickListener<MainCategory> {

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

    public CategoryAdapter(){
        super(DIFF_UTIL);
    }

    private OnAdapterItemClickListener onAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    @Override
    public void onClick(MainCategory mainCategory) {

    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_category_view,parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        MainCategory mainCategory = getItem(position);
        holder.categoryId.setText(mainCategory.getId());
        holder.name.setText(mainCategory.getName());
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        final TextView categoryId;
        final TextView name;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryId = itemView.findViewById(R.id.idNum);
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
