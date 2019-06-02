package com.soft.electronicroom.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.soft.electronicroom.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    //insert
    @Insert
    void save(Product product);

    //find all
    @Query("SELECT * FROM product")
    List<Product> findAll();

    // find by id
    @Query("SELECT * FROM product WHERE id = :id LIMIT 1")
    Product findById(int id);

    // remove
    @Delete
    void remove(Product product);

    //update
    @Update
    void update(Product product);

}
