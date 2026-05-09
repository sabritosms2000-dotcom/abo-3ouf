package com.example.Lab10.service;

import com.example.Lab10.model.Product;
import com.example.Lab10.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void addProduct(Product product) {
        repo.save(product);
    }


    public void updateProduct(Long id, String name, String description, double price) {
        Optional<Product> opt = repo.findById(id);
        if (opt.isEmpty()) return;
        Product product = opt.get();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        repo.save(product);
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}
