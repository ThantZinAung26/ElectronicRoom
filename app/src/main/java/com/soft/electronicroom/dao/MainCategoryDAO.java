package com.soft.electronicroom.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soft.electronicroom.model.MainCategory;

import java.util.List;

@Dao
public interface MainCategoryDAO {

    @Insert
    void save(MainCategory mainCategory);

    @Delete
    void delete(MainCategory mainCategory);

    @Query("SELECT * FROM main_category")
    List<MainCategory> findAll();

}
