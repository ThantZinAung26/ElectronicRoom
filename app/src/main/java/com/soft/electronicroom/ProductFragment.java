package com.soft.electronicroom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soft.electronicroom.adapter.ProductAdapter;
import com.soft.electronicroom.database.MainApplication;
import com.soft.electronicroom.model.MainCategory;
import com.soft.electronicroom.model.SubCategory;
import com.soft.electronicroom.repo.SubCatgoryRepo;

public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private SubCatgoryRepo subCatgoryRepo;
    private FloatingActionButton fab;
    private ProductAdapter productAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);

        productAdapter = new ProductAdapter();

        subCatgoryRepo = new SubCatgoryRepo(MainApplication.getCreateDatabase(getContext()).subCategoryDAO());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(productAdapter);

        fab = view.findViewById(R.id.btn_add);
        fab.setVisibility(View.INVISIBLE);
        fab.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fab.getViewTreeObserver().removeOnPreDrawListener(this);
                fab.postDelayed(fab::show, 100);
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                startActivity(intent);
            }
        });

        productAdapter.setOnItemClickListener(new ProductAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(SubCategory subCategory) {
                Intent intent = new Intent(ProductFragment.this.getActivity(), SubCategoryActivity.class);
                intent.putExtra(SubCategoryActivity.SUBCATEGORY_KEY_ID, subCategory.getId());
                ProductFragment.this.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Thread findThread = new Thread(new Runnable() {
            @Override
            public void run() {
                productAdapter.submitList(subCatgoryRepo.findAll());
            }
        });
        findThread.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        Thread findThread = new Thread(new Runnable() {
            @Override
            public void run() {
                productAdapter.submitList(subCatgoryRepo.findAll());
            }
        });
        findThread.start();
    }
}
