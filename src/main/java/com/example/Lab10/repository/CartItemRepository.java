

package com.example.Lab10.repository;

import com.example.Lab10.model.CartItem;
import com.example.Lab10.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);

    Optional<CartItem> findByUserAndProductId(User user, Long productId);

    void deleteByUser(User user);
}
