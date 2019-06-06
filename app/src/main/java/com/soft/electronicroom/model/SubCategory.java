package com.soft.electronicroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "sub_category", indices = @Index("mainCategory_id"),
                foreignKeys = @ForeignKey(entity = MainCategory.class,
                        parentColumns = "id",
                        childColumns = "mainCategory_id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE))
public class SubCategory {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @ColumnInfo(name = "mainCategory_id")
    private int mainCategoryId;

    @Ignore
    private MainCategory mainCategory;

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

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return id == that.id &&
                mainCategoryId == that.mainCategoryId &&
                name.equals(that.name) &&
                mainCategory.equals(that.mainCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mainCategoryId, mainCategory);
    }
}
