package com.ClotheShop.CShop.Facade.Review;

import com.ClotheShop.CShop.DTO.ReviewDTO;
import com.ClotheShop.CShop.Mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ClotheShop.CShop.Service.Review.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewFacadeImpl implements ReviewFacade {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @Autowired
    public ReviewFacadeImpl(ReviewMapper reviewMapper, ReviewService reviewService) {
        this.reviewMapper = reviewMapper;
        this.reviewService = reviewService;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews()
                .stream()
                .map(x -> reviewMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        return reviewMapper.toDTO(reviewService.addReview(reviewMapper.toEntity(reviewDTO)));
    }

    @Override
    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO) {
        return reviewMapper.toDTO(reviewService.updateReview(id, reviewMapper.toEntity(reviewDTO)));
    }

    @Override
    public void deleteReviewById(int id) {
        reviewService.deleteReviewById(id);
    }

    @Override
    public ReviewDTO getReviewById(int id) {
        return reviewMapper.toDTO(reviewService.getReviewById(id));
    }
}