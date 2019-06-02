package com.soft.electronicroom.repo;

import com.soft.electronicroom.dao.MainCategoryDAO;
import com.soft.electronicroom.model.MainCategory;

import java.util.List;

public class MainCategoryRepo {

    private MainCategoryDAO mainCategoryDAO;

    public MainCategoryRepo(MainCategoryDAO mainCategoryDAO) {
        this.mainCategoryDAO = mainCategoryDAO;
    }

    public void save(MainCategory mainCategory){
        mainCategoryDAO.save(mainCategory);
    }

    public List<MainCategory> findall(){
        return mainCategoryDAO.findAll();
    }
}
