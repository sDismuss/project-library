package com.netcracker.controller;

import com.netcracker.model.CartItem;
import com.netcracker.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionalCartController {
    @Autowired
    private CartItemService cartItemService;

    @RequestMapping("/cart/quantity/{id}/{quantity}")
    public boolean changeQuantity(@PathVariable("id") String id, @PathVariable("quantity") String quantity) {
        boolean answer = false;
        if(Integer.parseInt(quantity) > 0) {
            CartItem cartItem = cartItemService.findById(Integer.parseInt(id));
            cartItem.setQuantity(quantity);
            cartItemService.save(cartItem);
            answer = true;
        }
        return answer;
    }
}
