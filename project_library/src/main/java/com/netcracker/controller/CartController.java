package main.java.com.netcracker.controller;

import main.java.com.netcracker.model.Cart;
import main.java.com.netcracker.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/library/cart")
    public String cartForm(Model model) {
        List<Cart> cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping("/library/cart/{id}")
    public void addToCart(@PathVariable("id") String book) {
        String idCart = null;
        System.out.println("Успешно");
        List<Cart> carts = cartService.getCart();
        List<String> listID = new ArrayList<>();
        if (carts.size() != 0) {
            for (Cart cart : carts) {
                listID.add(cart.getId());
            }
            Collections.sort(listID);
        }

        System.out.println(listID.size());
        System.out.println(Integer.parseInt(listID.get(listID.size() - 1)));
        int i = 1;
        if (listID.size() != 0) {
            while (i < (listID.size() + 1)) {
                if (i == Integer.parseInt(listID.get(i - 1)))
                    i++;
                else {
                    idCart = Integer.toString(i);
                    break;
                }
            }
        }
        if (idCart == null)
            idCart = Integer.toString(i);
        System.out.println(idCart + " " + idCart.getClass());
        Cart nCart = new Cart(idCart, book);
        System.out.println(nCart.toString());
        cartService.save(nCart);
    }

    /*public List<String> sort(List<String> someList) {
        List<String> sortedList = new ArrayList<>();
        for (String num: someList) {
            boolean bool = false;
            for (int i = 0; i < sortedList.size(); i++){
                if (num.compareTo(sortedList.get(i)) < 0){
                    sortedList.add(i, num);
                    bool = true;
                    break;
                }
            }
            if(!bool)
                sortedList.add(num);
        }
        return sortedList;
    }*/
}
