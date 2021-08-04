package com.sawastha.ecomm.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.sawastha.ecomm.Model.CartItem;
import com.sawastha.ecomm.Model.Order;
import com.sawastha.ecomm.services.CartService;
import com.sawastha.ecomm.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    CartService cartService;
    ProductServices proService;

    public static Boolean validationWithHashMap(String keys[],HashMap<String,String> request) throws Exception{
        Boolean status = false;
        try {
            for(int start = 0;start<keys.length;start++) {

                if(request.containsKey(keys[start])) {//not exist
                    if(request.get(keys[start]).equals("")){//if empty
                        throw new Exception(keys[start]+" Should not be empty");
                    }
                }else {
                    throw new Exception(keys[start]+" is missing");
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Error is "+e.getMessage());
        }
        return status;
    }

    @RequestMapping("checkout_order")
    public ResponseEntity<?> checkout_order(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"userId","total_price","pay_type","deliveryAddress"};
            if(validationWithHashMap(keys, addCartRequest)) {


            }
            long user_Id = Long.parseLong(addCartRequest.get("userId"));
            double total_amt = Double.parseDouble(addCartRequest.get("total_price"));
            if(cartService.checkTotalAmountAgainstCart(total_amt,user_Id)) {
                List<CartItem> cartItems = cartService.getCartByUserId(user_Id);
                List<Order> tmp = new ArrayList<Order>();
                for(CartItem addCart : cartItems) {
                    String orderId = ""+getOrderId();
                    Order cart = new Order();
                    cart.setPayment_type(addCartRequest.get("pay_type"));
                    cart.setPrice(total_amt);
                    cart.setUser_id(user_Id);
                    cart.setOrder_id(orderId);
                    cart.setProduct(addCart.getProduct());
                    cart.setQty(addCart.getQty());
                    cart.setDelivery_address(addCartRequest.get("deliveryAddress"));
                    tmp.add(cart);
                }
                cartService.saveProductsForCheckout(tmp);
                return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
            }else {
                throw new Exception("Total amount is mismatch");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
    public int getOrderId() {
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }
    @RequestMapping("getOrdersByUserId")
    public ResponseEntity<?> getOrdersByUserId(@RequestBody HashMap<String,String> ordersRequest) {
        try {
            String keys[] = {"userId"};
            return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }
}

