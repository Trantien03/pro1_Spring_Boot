package com.example.pro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository đã cung cấp sẵn các phương thức như save, deleteById, findById, findAll, v.v.
}
