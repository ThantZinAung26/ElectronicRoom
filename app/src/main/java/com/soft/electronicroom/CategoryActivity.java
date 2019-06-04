package com.soft.electronicroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.soft.electronicroom.database.MainApplication;
import com.soft.electronicroom.model.MainCategory;
import com.soft.electronicroom.repo.MainCategoryRepo;

public class CategoryActivity extends AppCompatActivity {

    private TextInputEditText categoryName;
    private Button saveBtn;

    private MainCategoryRepo categoryRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryName = findViewById(R.id.ed_title);
        saveBtn = findViewById(R.id.categorySave);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCategory();
            }
        });
    }

    private void saveCategory() {
        final String cName = categoryName.getText().toString();
        if (cName.isEmpty()) {
            categoryName.setError("Name required!");
            categoryName.requestFocus();
            return;
        }

        class SaveCategory extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MainCategory mainCategory = new MainCategory();
                mainCategory.setName(cName);

                MainApplication.getInstance(getApplicationContext()).getCreateDatabase().mainCategoryDAO().save(mainCategory);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                //startActivity(new Intent(getApplicationContext(), MainApplication.class));
                Toast.makeText(getApplicationContext(), "Save Finish.", Toast.LENGTH_SHORT).show();
            }
        }

        SaveCategory sc = new SaveCategory();
        sc.execute();
    }
}
