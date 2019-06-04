package com.soft.electronicroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product", indices = @Index("subCategory_id"),
                foreignKeys = @ForeignKey(entity = SubCategory.class,
                            parentColumns = "id",
                            childColumns = "subCategory_id",
                            onUpdate = ForeignKey.CASCADE,
                            onDelete = ForeignKey.CASCADE))
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private double price;

    private int rate;

    @ColumnInfo(name = "subCategory_id")
    private int subCategory;

    private String description;

    private boolean favourate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavourate() {
        return favourate;
    }

    public void setFavourate(boolean favourate) {
        this.favourate = favourate;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }
}
