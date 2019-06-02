package com.soft.electronicroom;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.soft.electronicroom.database.MainApplication;
import com.soft.electronicroom.model.MainCategory;
import com.soft.electronicroom.repo.MainCategoryRepo;

public class CategoryActivity extends AppCompatActivity {

    private TextInputEditText categoryName;
    private Button saveBtn;
    private ArrayAdapter<MainCategory> categoryArrayAdapter;
    private MainCategoryRepo categoryRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryName = findViewById(R.id.ed_title);
        saveBtn = findViewById(R.id.categorySave);

        categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        categoryRepo = new MainCategoryRepo(MainApplication.getInstance(getApplicationContext())
                .getCreateDatabase().mainCategoryDAO());

        categoryArrayAdapter.addAll(categoryRepo.findall());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainCategory mainCategory = new MainCategory();

                mainCategory.setName(categoryName.getText().toString());

                finish();

            }
        });

    }
}
