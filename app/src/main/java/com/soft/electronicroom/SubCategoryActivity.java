package com.soft.electronicroom;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.soft.electronicroom.database.MainApplication;
import com.soft.electronicroom.model.MainCategory;
import com.soft.electronicroom.model.SubCategory;
import com.soft.electronicroom.repo.MainCategoryRepo;

public class SubCategoryActivity extends AppCompatActivity {

    private TextInputEditText editTitle;
    private TextInputEditText editReleaseDate;
    private TextInputEditText editCategory;

    private SubCategory subCategory;
    private MainCategoryRepo mainCategoryRepo;
    private ArrayAdapter<MainCategory> categoryArrayAdapter;

    private Spinner spinnerDate;
    private Spinner spinnerCategory;

    private Button btnSave;
    private Button btn_Delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        editTitle = findViewById(R.id.edtitle);
        editReleaseDate = findViewById(R.id.edreleaseDate);
        editCategory = findViewById(R.id.edCategory);

        spinnerDate = findViewById(R.id.date_spinner);
        spinnerCategory = findViewById(R.id.category_spinner);

        mainCategoryRepo = new MainCategoryRepo(MainApplication.getInstance(this).getCreateDatabase().mainCategoryDAO());

        categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        categoryArrayAdapter.addAll(mainCategoryRepo.findAll());

        /*MainCategory mainCategory = mainCategoryRepo.findById(subCategory.getMainCategoryId());*/

        subCategory = new SubCategory();

    }
}
