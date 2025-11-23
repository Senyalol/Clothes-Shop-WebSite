package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {

    List<ProductImage> findByProductId(int productId);
    Optional<ProductImage> findById(int id);

}