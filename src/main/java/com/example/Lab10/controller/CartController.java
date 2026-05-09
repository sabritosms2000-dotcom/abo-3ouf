//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.Lab10.controller;

import com.example.Lab10.model.CartItem;
import com.example.Lab10.model.User;
import com.example.Lab10.service.CartService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/cart"})
public class CartController {
    private final CartService cartService;

    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            List<CartItem> items = this.cartService.getCartItems(user);
            model.addAttribute("cartItems", items);
            model.addAttribute("total", this.cartService.getTotal(items));
            return "cart";
        }
    }

    @PostMapping({"/add"})
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            this.cartService.addToCart(user, productId);
            return "redirect:/products";
        }
    }

    @PostMapping({"/remove/{id}"})
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            this.cartService.removeFromCart(id);
            return "redirect:/cart";
        }
    }
}
