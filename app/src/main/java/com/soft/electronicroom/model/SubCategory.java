package com.soft.electronicroom.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

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

    private Date release;

    @ColumnInfo(name = "mainCategory_id")
    private int mainCategoryId;

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

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public int getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }
}
