package com.soft.electronicroom;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
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
import com.soft.electronicroom.repo.SubCatgoryRepo;

public class SubCategoryActivity extends AppCompatActivity {

    static final String SUBCATEGORY_KEY_ID = "subcategory_id";

    private TextInputEditText editTitle;
    private TextInputEditText editCategory;

    private SubCategory subCategory;
    private MainCategoryRepo mainCategoryRepo;
    private SubCatgoryRepo subCatgoryRepo;
    private ArrayAdapter<MainCategory> categoryArrayAdapter;

    private Spinner spinnerCategory;

    private Button btnSave;
    private Button btn_Delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        int id = getIntent().getIntExtra(SUBCATEGORY_KEY_ID, 0);

        editTitle = findViewById(R.id.edtitle);
        editCategory = findViewById(R.id.edCategory);
        spinnerCategory = findViewById(R.id.category_spinner);

        btnSave = findViewById(R.id.btn_save);
        btn_Delete = findViewById(R.id.delete_btn);

        categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        mainCategoryRepo = new MainCategoryRepo(MainApplication.getCreateDatabase(this).mainCategoryDAO());
        subCatgoryRepo = new SubCatgoryRepo(MainApplication.getCreateDatabase(this).subCategoryDAO());

        Thread adapterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                spinnerCategory.post(() -> spinnerCategory.setAdapter(categoryArrayAdapter));
                categoryArrayAdapter.addAll(mainCategoryRepo.findAll());
                Log.d("TAG", "" + mainCategoryRepo.findAll().size());
            }
        });

        adapterThread.start();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (id > 0) {
            Thread findThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    subCategory = subCatgoryRepo.findById(id);
                    editTitle.post(() -> editTitle.setText(subCategory.getName()));
                    MainCategory mainCategory = mainCategoryRepo.findById(subCategory.getMainCategoryId());
                    editCategory.post(() -> editCategory.setText(mainCategory.getName()));
                    for (int i = 0; i < categoryArrayAdapter.getCount(); i++) {
                        MainCategory maCategory = categoryArrayAdapter.getItem(i);
                        if (maCategory.getId() == subCategory.getMainCategoryId()) {
                            spinnerCategory.setSelection(i);
                            break;
                        }
                    }
                }
            });
            findThread.start();
        } else {
            subCategory = new SubCategory();
        }

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainCategory mainCategory = categoryArrayAdapter.getItem(position);
                editCategory.setText(mainCategory.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editCategory.setOnKeyListener((v, keyCode, event) -> true);

        editCategory.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                spinnerCategory.performClick();
            }
            return true;
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread saveThread = new Thread(() -> {
                    subCategory.setName(editTitle.getText().toString());
                    MainCategory mainCategory = (MainCategory) spinnerCategory.getSelectedItem();
                    subCategory.setMainCategoryId(mainCategory.getId());
                    subCatgoryRepo.save(subCategory);
                });
                saveThread.start();
                finish();
            }
        });

        btn_Delete.setOnClickListener(v -> {
            Thread deleteThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    subCatgoryRepo.delete(subCategory);
                }
            });
            deleteThread.start();
            finish();
        });

    }
}
