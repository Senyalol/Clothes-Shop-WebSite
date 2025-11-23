package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.ShopCan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopCanRepository extends JpaRepository<ShopCan, Integer> {

    Optional<ShopCan> findById(int id);
    List<ShopCan> findByUserId(int userId);

}