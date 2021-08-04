package com.sawastha.ecomm.Controller;

import java.util.HashMap;
import java.util.List;

import com.sawastha.ecomm.Model.CartItem;
import com.sawastha.ecomm.Model.Product;
import com.sawastha.ecomm.services.CartSerivceImpl;
import com.sawastha.ecomm.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cartItem")
public class CartItemController {

    @Autowired
    CartSerivceImpl cartService;

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

    @RequestMapping("/addProduct")
    public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"productId","userId","qty","price"};
            if(validationWithHashMap(keys, addCartRequest)) {

            }
            long productId = Long.parseLong(addCartRequest.get("productId"));
            long userId =  Long.parseLong(addCartRequest.get("userId"));
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double price = Double.parseDouble(addCartRequest.get("price"));
            List<CartItem> obj = cartService.addCartbyUserIdAndProductId(productId,userId,qty,price);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @RequestMapping("/updateQtyForCart")
    public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest) {
        try {
            String keys[] = {"cartId","userId","qty","price"};
            if(validationWithHashMap(keys, addCartRequest)) {

            }
            long cartId = Long.parseLong(addCartRequest.get("cartId"));
            long userId =  Long.parseLong(addCartRequest.get("userId"));
            int qty =  Integer.parseInt(addCartRequest.get("qty"));
            double price = Double.parseDouble(addCartRequest.get("price"));
            cartService.updateQtyByCartId(cartId, qty, price);
            List<CartItem> obj = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }

    }

    @RequestMapping("/removeProductFromCart")
    public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest) {
        try {
            String keys[] = {"userId","cartId"};
            if(validationWithHashMap(keys, removeCartRequest)) {

            }
            List<CartItem> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")), Long.parseLong(removeCartRequest.get("userId")));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @RequestMapping("/getCartsByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
        try {
            String keys[] = {"userId"};
            if(validationWithHashMap(keys, getCartRequest)) {
            }
            List<CartItem> obj = cartService.getCartByUserId(Long.parseLong(getCartRequest.get("userId")));
            return ResponseEntity.ok(obj);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @RequestMapping("/getCartItems")
    public List<CartItem> getAllCartItems(){
        return cartService.getAllCartItems();
    }
}

