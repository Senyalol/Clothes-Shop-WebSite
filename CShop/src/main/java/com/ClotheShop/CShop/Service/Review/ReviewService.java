package com.ClotheShop.CShop.Service.Review;

import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Entity.User;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews();

    Review addReview(Review review);

    Review updateReview(int id,Review review);

    void deleteReviewById(int id);

    Review getReviewById(int id);

    Review changeYouReview(int id, Review review);

    void deleteYouReview(int id, Review review);

}