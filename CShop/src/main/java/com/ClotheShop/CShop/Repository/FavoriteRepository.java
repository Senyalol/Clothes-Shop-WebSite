package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findAllByUserId(int userId);
    Optional<Favorite> findById(int id);

}