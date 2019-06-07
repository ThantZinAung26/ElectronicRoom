package com.soft.electronicroom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.soft.electronicroom.database.MainApplication;
import com.soft.electronicroom.model.MainCategory;
import com.soft.electronicroom.repo.MainCategoryRepo;

public class CategoryActivity extends AppCompatActivity {

    static final String CATEGORY_KEY_ID = "mainCategory_id";

    private TextInputEditText categoryName;
    private Button saveBtn;

    private MainCategoryRepo categoryRepo;
    private MainCategory mainCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int id = getIntent().getIntExtra(CATEGORY_KEY_ID,0);

        categoryName = findViewById(R.id.ed_title);
        saveBtn = findViewById(R.id.categorySave);

        categoryRepo = new MainCategoryRepo(MainApplication.getCreateDatabase(this).mainCategoryDAO());

        if (id > 0) {

            Thread findThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    mainCategory = categoryRepo.findById(id);
                    //final String name = categoryRepo.findById(id).getName();
                    Log.d("ID","id_"+mainCategory.getName());
                    //TODO ERROR categoryName.setText(name);
                    categoryName.post(new Runnable() {
                        @Override
                        public void run() {
                            categoryName.setText(mainCategory.getName());
                        }
                    });

                }
            });
            findThread.start();

        } else {
            mainCategory = new MainCategory();
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCategoryCheck();
                mainCategory.setName(categoryName.getText().toString());
                Thread saveThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        categoryRepo.save(mainCategory);
                    }
                });
                saveThread.start();
                saveCategoryCheck();
                finish();
            }
        });
    }

    private void saveCategoryCheck() {
        final String cName = categoryName.getText().toString();
        if (cName.isEmpty()) {
            categoryName.setError("Name required!");
            categoryName.requestFocus();
            return;
        }

    }
}
