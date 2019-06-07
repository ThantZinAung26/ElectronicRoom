package com.soft.electronicroom.repo;

import com.soft.electronicroom.dao.ProductDAO;
import com.soft.electronicroom.model.Product;
import com.soft.electronicroom.model.SubCategory;

import java.util.List;

public class ProductRepo {

    private ProductDAO productDAO;

    public ProductRepo(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void save(Product product) {
        if (product.getId() > 0) {
            productDAO.update(product);
        } else {
            productDAO.save(product);
        }
    }

    public List<Product> findAll(){
        return productDAO.findAll();
    }

    public void delete(Product product){
        productDAO.remove(product);
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }

}
