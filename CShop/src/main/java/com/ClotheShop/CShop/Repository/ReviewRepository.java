package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    public Review findById(int id);
    public List<Review> findByProduct_Id(int id);
    public List<Review> findByUser_Id(int id);

}