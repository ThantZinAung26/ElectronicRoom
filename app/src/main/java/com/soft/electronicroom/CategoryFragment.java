package com.soft.electronicroom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        categoryAdapter = new CategoryAdapter();
        categoryRepo = new MainCategoryRepo(MainApplication.getInstance(getContext())
                .getCreateDatabase()
                .mainCategoryDAO());


        recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
//      recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));

        recyclerView.setAdapter(categoryAdapter);

        FloatingActionButton fab = view.findViewById(R.id.btn_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                startActivity(intent);
            }
        });
        getMainCategory();
        return view;
    }


    public void getMainCategory() {
        class GetMainCategory extends AsyncTask<Void, Void, List<MainCategory>> {

            @Override
            protected List<MainCategory> doInBackground(Void... voids) {
                categoryRepo = new MainCategoryRepo(MainApplication.getInstance(getContext())
                .getCreateDatabase()
                .mainCategoryDAO());
                Log.d("TAG", "" + categoryRepo.findAll().size());
                return categoryRepo.findAll();
            }

            @Override
            protected void onPostExecute(List<MainCategory> mainCategories) {
                super.onPostExecute(mainCategories);
                categoryAdapter.submitList(mainCategories);
            }
        }

        GetMainCategory gm = new GetMainCategory();
        gm.execute();
    }

}
