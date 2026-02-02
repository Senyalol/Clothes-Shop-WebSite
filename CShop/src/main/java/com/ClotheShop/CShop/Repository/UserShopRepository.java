package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.UserShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserShopRepository extends JpaRepository<UserShop, Integer> {

    Optional<UserShop> findById(int id);
    List<UserShop> findByUserId(int userId);

}