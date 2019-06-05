package com.soft.electronicroom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soft.electronicroom.adapter.CategoryAdapter;
import com.soft.electronicroom.database.MainApplication;
import com.soft.electronicroom.model.MainCategory;
import com.soft.electronicroom.repo.MainCategoryRepo;

import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryAdapter categoryAdapter;
    private MainCategoryRepo categoryRepo;
    private RecyclerView recyclerView;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        categoryAdapter = new CategoryAdapter();
        categoryRepo = new MainCategoryRepo(MainApplication.getCreateDatabase(getContext()).mainCategoryDAO());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(categoryAdapter);

        final FloatingActionButton fab = view.findViewById(R.id.btn_add);
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
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        categoryAdapter.setOnAdapterItemClickListener(new CategoryAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(MainCategory category) {
                Intent intent = new Intent(CategoryFragment.this.getActivity(), CategoryActivity.class);
                intent.putExtra(CategoryActivity.CATEGORY_KEY_ID, category.getId());
                CategoryFragment.this.startActivity(intent);
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
                categoryAdapter.submitList(categoryRepo.findAll());
            }
        });
        findThread.start();
    }
}
