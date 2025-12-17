package com.ClotheShop.CShop.Facade.Review;

import com.ClotheShop.CShop.DTO.ReviewDTO;

import java.util.List;

public interface ReviewFacade {

    List<ReviewDTO> getAllReviews();

    ReviewDTO addReview(String token, ReviewDTO reviewDTO);

    ReviewDTO updateReview(int id,ReviewDTO reviewDTO);

    void deleteReviewById(int id);

    ReviewDTO getReviewById(int id);

    ReviewDTO changeYouReview(int id, ReviewDTO reviewDTO, String token);

    void deleteYouReview(int id, ReviewDTO reviewDTO , String token);

}