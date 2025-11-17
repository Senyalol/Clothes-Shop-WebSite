package com.ClotheShop.CShop.Facade.Review;

import com.ClotheShop.CShop.DTO.ReviewDTO;

import java.util.List;

public interface ReviewFacade {

    List<ReviewDTO> getAllReviews();

    ReviewDTO addReview(ReviewDTO reviewDTO);

    ReviewDTO updateReview(int id,ReviewDTO reviewDTO);

    void deleteReviewById(int id);

    ReviewDTO getReviewById(int id);

}