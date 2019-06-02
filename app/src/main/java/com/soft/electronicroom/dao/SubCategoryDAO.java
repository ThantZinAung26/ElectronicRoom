package com.soft.electronicroom.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.soft.electronicroom.model.SubCategory;

import java.util.List;

@Dao
public interface SubCategoryDAO {

    //insert
    @Insert
    void save(SubCategory subCategory);

    //find all
    @Query("SELECT * FROM sub_category")
    List<SubCategory> findAll();

    // find by id
    @Query("SELECT * FROM sub_category WHERE id = :id LIMIT 1")
    SubCategory findById(int id);

    // remove
    @Delete
    void remove(SubCategory subCategory);

    //update
    @Update
    void update(SubCategory subCategory);

}
