package com.soft.electronicroom.repo;

import com.soft.electronicroom.dao.SubCategoryDAO;
import com.soft.electronicroom.model.SubCategory;

import java.util.List;

public class SubCatgoryRepo {

    private SubCategoryDAO subCategoryDAO;

    public SubCatgoryRepo(SubCategoryDAO subCategoryDAO) {
        this.subCategoryDAO = subCategoryDAO;
    }

    public void save(SubCategory subCategory) {
        if (subCategory.getId() > 0) {
            subCategoryDAO.update(subCategory);
        } else {
            subCategoryDAO.save(subCategory);
        }
    }

    public List<SubCategory> findAll(){
        return subCategoryDAO.findAll();
    }

    public void delete(SubCategory subCategory){
        subCategoryDAO.remove(subCategory);
    }

    public SubCategory findById(int id) {
        return subCategoryDAO.findById(id);
    }

}
