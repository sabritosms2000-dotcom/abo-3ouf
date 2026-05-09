//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.Lab10.controller;

import com.example.Lab10.model.Product;
import com.example.Lab10.model.User;
import com.example.Lab10.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    private final ProductService productService;

    AdminController(ProductService productService) {
        this.productService = productService;
    }

    private boolean isAdmin(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return user != null && "ADMIN".equals(user.getRole());
    }

    @GetMapping
    public String adminPage(HttpSession session, Model model) {
        if (!this.isAdmin(session)) {
            return "redirect:/login";
        } else {
            model.addAttribute("products", this.productService.getAllProducts());
            model.addAttribute("newProduct", new Product());
            return "admin";
        }
    }

    @PostMapping({"/add"})
    public String addProduct(@ModelAttribute Product newProduct, HttpSession session) {
        if (!this.isAdmin(session)) {
            return "redirect:/login";
        } else {
            this.productService.addProduct(newProduct);
            return "redirect:/admin";
        }
    }

    @PostMapping({"/edit/{id}"})
    public String editProduct(@PathVariable Long id, @RequestParam String name, @RequestParam(required = false) String description, @RequestParam double price, HttpSession session) {
        if (!this.isAdmin(session)) {
            return "redirect:/login";
        } else {
            this.productService.updateProduct(id, name, description, price);
            return "redirect:/admin";
        }
    }

    @PostMapping({"/delete/{id}"})
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        if (!this.isAdmin(session)) {
            return "redirect:/login";
        } else {
            this.productService.deleteProduct(id);
            return "redirect:/admin";
        }
    }
}
