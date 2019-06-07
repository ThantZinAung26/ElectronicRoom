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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.soft.electronicroom.adapter.ProductAdapter;
import com.soft.electronicroom.database.MainApplication;
import com.soft.electronicroom.model.Product;
import com.soft.electronicroom.model.SubCategory;
import com.soft.electronicroom.repo.ProductRepo;
import com.soft.electronicroom.repo.SubCatgoryRepo;

public class ProductActivity extends AppCompatActivity {

    static final String PRODUCT_KEY_ID = "product_id";

    private TextInputEditText edName;
    private TextInputEditText edPrice;
    private TextInputEditText edDescription;
    private TextInputEditText edSubCategory;

    private Spinner subCategorySpinner;

    private Button btnSave;
    private Button btn_Delete;

    private ProductAdapter productAdapter;

    private Product product;

    private ProductRepo productRepo;
    private SubCatgoryRepo subCatgoryRepo;

    private ArrayAdapter<SubCategory> subCategoryArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int id = getIntent().getIntExtra(PRODUCT_KEY_ID, 0);

        edName = findViewById(R.id.ed_name);
        edPrice = findViewById(R.id.ed_price);
        edDescription = findViewById(R.id.ed_description);
        subCategorySpinner = findViewById(R.id.spinnerSubCategory);
        edSubCategory = findViewById(R.id.edSubCategory);

        btnSave = findViewById(R.id.btn_save);
        btn_Delete = findViewById(R.id.btn_delete);

        subCategoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        productAdapter = new ProductAdapter();
        productRepo = new ProductRepo(MainApplication.getCreateDatabase(this).productDAO());

        Thread adapterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                subCategoryArrayAdapter.addAll(subCatgoryRepo.findAll());
                Log.d("TAG", "" + subCatgoryRepo.findAll().size());
                subCategorySpinner.post(new Runnable() {
                    @Override
                    public void run() {
                        subCategorySpinner.setAdapter(subCategoryArrayAdapter);
                    }
                });

            }
        });

        adapterThread.start();

        if (id > 0) {
            Thread findThread = new Thread(new Runnable() {
                @Override
                public void run() {
                     product = productRepo.findById(id);
                    edName.post(new Runnable() {
                        @Override
                        public void run() {
                            edName.setText(product.getName());
                        }
                    });
                    SubCategory subCategory = subCatgoryRepo.findById(product.getSubCategoryId());
                    edSubCategory.post(new Runnable() {
                        @Override
                        public void run() {
                            edSubCategory.setText(subCategory.getName());
                        }
                    });
                }
            });
            findThread.start();
        } else {
            product = new Product();
        }

        subCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SubCategory subCategory = subCategoryArrayAdapter.getItem(position);
                edSubCategory.setText(subCategory.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edSubCategory.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;
            }
        });

        edSubCategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

       /* btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread saveThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        subCategory.setName(editTitle.getText().toString());
                        MainCategory mainCategory = (MainCategory) spinnerCategory.getSelectedItem();
                        subCategory.setMainCategoryId(mainCategory.getId());
                        subCatgoryRepo.save(subCategory);
                    }
                });
                saveThread.start();
                finish();
            }
        });*/

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread deleteThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        productRepo.delete(product);
                    }
                });

                deleteThread.start();
                finish();
            }
        });

    }
}
