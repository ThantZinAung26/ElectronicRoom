package com.soft.electronicroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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

    @ColumnInfo(name = "subCategory_id")
    private int subCategoryId;

    private String description;

    private boolean favourite;

    @Ignore
    private SubCategory subCategory;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }
}
