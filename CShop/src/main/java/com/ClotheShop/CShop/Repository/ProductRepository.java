package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(int id);
    Optional<Product> findByName(String name);
    Optional<Product> findBySex(String sex);
    Optional<Product> findByCategory(String category);
    Optional<Product> findByType(String type);

}
