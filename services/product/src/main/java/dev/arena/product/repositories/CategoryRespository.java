package dev.arena.product.repositories;

import dev.arena.product.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRespository extends JpaRepository <Category, UUID> {
}
