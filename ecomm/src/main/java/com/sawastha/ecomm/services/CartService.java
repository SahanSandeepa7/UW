package com.sawastha.ecomm.services;

import java.util.List;

import com.sawastha.ecomm.Model.CartItem;
import com.sawastha.ecomm.Model.Category;
import com.sawastha.ecomm.Model.Order;
import com.sawastha.ecomm.Repository.CartItemRepository;
import com.sawastha.ecomm.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface CartService {



    List<CartItem> addCartbyUserIdAndProductId(long productId,long userId,int qty,double price) throws Exception;
    void updateQtyByCartId(long cartId,int qty,double price) throws Exception;
    List<CartItem> getCartByUserId(long userId);
    List<CartItem> removeCartByUserId(long cartId,long userId);
    List<CartItem> removeAllCartByUserId(long userId);
    Boolean checkTotalAmountAgainstCart(double totalAmount,long userId);
    List<Order> getAllCheckoutByUserId(long userId);
    List<Order> saveProductsForCheckout(List<Order> tmp)  throws Exception;

    List<CartItem> getAllCartItems();

    //CheckOutCart
}
