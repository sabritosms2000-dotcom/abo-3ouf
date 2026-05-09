package com.example.Lab10.service;

import com.example.Lab10.model.CartItem;
import com.example.Lab10.model.Product;
import com.example.Lab10.model.User;
import com.example.Lab10.repository.CartItemRepository;
import com.example.Lab10.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartItemRepository cartRepo;
    private final ProductRepository productRepo;

    CartService(CartItemRepository cartRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public List<CartItem> getCartItems(User user) {
        return cartRepo.findByUser(user);
    }

    public void addToCart(User user, Long productId) {
        Optional<CartItem> existing = cartRepo.findByUserAndProductId(user, productId);
        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + 1);
            cartRepo.save(item);
        } else {
            Product product = productRepo.findById(productId).orElse(null);
            if (product == null) return;
            CartItem item = new CartItem();
            item.setUser(user);
            item.setProduct(product);
            item.setQuantity(1);
            cartRepo.save(item);
        }
    }

    public void removeFromCart(Long cartItemId) {
        cartRepo.deleteById(cartItemId);
    }

    public double getTotal(List<CartItem> items) {
        return items.stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
    }
}
