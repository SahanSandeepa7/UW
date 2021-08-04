package com.sawastha.ecomm.Repository;

import java.util.List;

import com.sawastha.ecomm.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select pro FROM Product pro WHERE pro.category_id=:cat_id")
    List<Product> getByCategoryId(@Param("cat_id")String cat_id);
}

