package com.sawastha.ecomm.services;

import java.util.List;

import com.sawastha.ecomm.Model.Category;
import com.sawastha.ecomm.Model.Product;
import com.sawastha.ecomm.Repository.CategoryRepository;
import com.sawastha.ecomm.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    CategoryRepository cateRepo;

    public List<Product>getAllProducts(){
        return productRepo.findAll();
    }
    public List<Product>getProductsByCategory(String product_id){
        return productRepo.getByCategoryId(product_id);
    }

    public List<Category>getAllCategory(){
        return cateRepo.findAll();
    }

    public Product getProductsById(long productId) throws Exception {
        return productRepo.findById(productId).orElseThrow(() ->new Exception("Product is not found"));
    }
}

