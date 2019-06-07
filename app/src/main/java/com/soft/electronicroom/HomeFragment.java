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
import com.soft.electronicroom.repo.ProductRepo;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductRepo productRepo;
    private FloatingActionButton fab;
    private ProductAdapter productAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        productAdapter = new ProductAdapter();
        productRepo = new ProductRepo(MainApplication.getCreateDatabase(getContext()).productDAO());

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
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        });

        productAdapter.setOnAdapterItemClickListener(product -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), HomeActivity.class);
            intent.putExtra(HomeActivity.PRODUCT_KEY_ID, product.getId());
            HomeFragment.this.startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Thread findThread = new Thread(() -> productAdapter.submitList(productRepo.findAll()));
        findThread.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        Thread findThread = new Thread(() -> productAdapter.submitList(productRepo.findAll()));
        findThread.start();
    }

}
