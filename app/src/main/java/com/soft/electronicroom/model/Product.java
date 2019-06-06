package com.soft.electronicroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

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

    private float rate;

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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                subCategoryId == product.subCategoryId &&
                Float.compare(product.rate, rate) == 0 &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                Objects.equals(subCategory, product.subCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, subCategoryId, description, rate, subCategory);
    }
}
