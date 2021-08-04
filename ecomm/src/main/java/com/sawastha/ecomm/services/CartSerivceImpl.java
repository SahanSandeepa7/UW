package com.sawastha.ecomm.services;

import java.util.List;

import com.sawastha.ecomm.Model.CartItem;
import com.sawastha.ecomm.Model.Order;
import com.sawastha.ecomm.Model.Product;
import com.sawastha.ecomm.Repository.CartItemRepository;
import com.sawastha.ecomm.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.youtube.ecommerce.Repository.AddToCartRepo;
//import com.youtube.ecommerce.Repository.CheckoutRepo;
//import com.youtube.ecommerce.model.AddtoCart;
//import com.youtube.ecommerce.model.CheckoutCart;
//import com.youtube.ecommerce.model.Products;
//import com.youtube.ecommerce.service.ProductService.ProductServices;

@Service
public class CartSerivceImpl implements CartService {

    @Autowired
    CartItemRepository addCartRepo;
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    ProductServices proServices;

    @Autowired
    CartItemRepository cartRepo;

    private static final Logger logger = LoggerFactory.getLogger(CartSerivceImpl.class);

    @Override
    public List<CartItem> addCartbyUserIdAndProductId(long productId, long userId, int qty, double price) throws Exception {
        try {
            if(addCartRepo.getCartByProductIdAnduserId(userId, productId).isPresent()){
                throw new Exception("Product is already exist.");
            }
            CartItem obj = new CartItem();
            obj.setQty(qty);
            obj.setUser_id(userId);
            Product pro = proServices.getProductsById(productId);
            obj.setProduct(pro);
            //TODO price has to check with qty
            obj.setPrice(price);
            addCartRepo.save(obj);
            return this.getCartByUserId(userId);
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(""+e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<CartItem> getCartByUserId(long userId) {
        return addCartRepo.getCartByuserId(userId);
    }

    @Override
    public List<CartItem> removeCartByUserId(long cartId, long userId) {
        addCartRepo.deleteCartByIdAndUserId(userId, cartId);
        return this.getCartByUserId(userId);
    }

    @Override
    public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
        addCartRepo.updateQtyByCartId(cartId,price,qty);
    }

    @Override
    public Boolean checkTotalAmountAgainstCart(double totalAmount,long userId) {
        double total_amount =addCartRepo.getTotalAmountByUserId(userId);
        if(total_amount == totalAmount) {
            return true;
        }
        System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
        return false;
    }

    @Override
    public List<Order> getAllCheckoutByUserId(long userId) {
        return orderRepo.getByuserId(userId);
    }

    @Override
    public List<Order> saveProductsForCheckout(List<Order> tmp) throws Exception {
        try {
            long user_id = tmp.get(0).getUser_id();
            if(tmp.size() >0) {
                orderRepo.saveAll(tmp);
                this.removeAllCartByUserId(user_id);
                return this.getAllCheckoutByUserId(user_id);
            }
            else {
                throw  new Exception("Should not be empty");
            }
        }catch(Exception e) {
            throw new Exception("Error while checkout "+e.getMessage());
        }

    }

    @Override
    public List<CartItem> removeAllCartByUserId(long userId) {
        addCartRepo.deleteAllCartByUserId(userId);
        return null;
    }

    @Override
    public List<CartItem> getAllCartItems(){
        return cartRepo.findAll();
    }

}

