package com.sawastha.ecomm.Repository;

import java.util.List;

import com.sawastha.ecomm.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
    @Query("Select order  FROM Order order WHERE order.user_id=:user_id")
    List<Order> getByuserId(@Param("user_id")Long user_id);
}
