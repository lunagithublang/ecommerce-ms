package dev.arena.product.repositories;

import dev.arena.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository <Product, UUID> {
    List<Product> findAllByIdInOrderById(List<UUID> productIds);
}
