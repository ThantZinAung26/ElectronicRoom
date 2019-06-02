package com.soft.electronicroom.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.soft.electronicroom.converter.Converter;
import com.soft.electronicroom.dao.MainCategoryDAO;
import com.soft.electronicroom.dao.ProductDAO;
import com.soft.electronicroom.dao.SubCategoryDAO;
import com.soft.electronicroom.model.MainCategory;
import com.soft.electronicroom.model.Product;
import com.soft.electronicroom.model.SubCategory;

@TypeConverters(Converter.class)
@Database(entities = {MainCategory.class, SubCategory.class, Product.class}, version = 1,exportSchema = false)
public abstract class CreateDatabase extends RoomDatabase {

    public abstract MainCategoryDAO mainCategoryDAO();

    public abstract SubCategoryDAO subCategoryDAO();

    public abstract ProductDAO productDAO();

}
