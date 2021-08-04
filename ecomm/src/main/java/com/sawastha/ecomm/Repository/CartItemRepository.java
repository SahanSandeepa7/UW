package com.sawastha.ecomm.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.sawastha.ecomm.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {


    //remove cart by userid and cartId,
    //getCartByuserId

    @Query("Select sum(cartItem.price) FROM CartItem cartItem WHERE cartItem.user_id=:user_id")
    double getTotalAmountByUserId(@Param("user_id")Long user_id);
    @Query("Select cartItem  FROM CartItem cartItem WHERE cartItem.user_id=:user_id")
    List<CartItem> getCartByuserId(@Param("user_id")Long user_id);
    @Query("Select cartItem  FROM CartItem cartItem ")
    Optional<CartItem> getCartByuserIdtest();
    @Query("Select cartItem  FROM CartItem cartItem WHERE cartItem.product.id= :product_id and cartItem.user_id=:user_id")
    Optional<CartItem> getCartByProductIdAnduserId(@Param("user_id")Long user_id,@Param("product_id")Long product_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM CartItem cartItem WHERE cartItem.id =:cart_id   and cartItem.user_id=:user_id")
    void deleteCartByIdAndUserId(@Param("user_id")Long user_id,@Param("cart_id")Long cart_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM CartItem cartItem WHERE   cartItem.user_id=:user_id")
    void deleteAllCartByUserId(@Param("user_id")Long user_id);

    @Modifying
    @Transactional
    @Query("DELETE  FROM CartItem cartItem WHERE cartItem.user_id=:user_id")
    void deleteAllCartUserId(@Param("user_id")Long user_id);
    @Modifying
    @Transactional
    @Query("update CartItem cartItem set cartItem.qty=:qty,cartItem.price=:price WHERE cartItem.id=:cart_id")
    void updateQtyByCartId(@Param("cart_id")Long cart_id,@Param("price")double price,@Param("qty")Integer qty);

}


