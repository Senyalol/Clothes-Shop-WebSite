package com.ClotheShop.CShop.Service.Review;

import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.ReviewRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Service.Review.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.Review.Checks.UpdateChecks.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@JsonSerialize
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private static Logger LOGGER = LogManager.getLogger(ReviewServiceImpl.class);

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,UserRepository userRepository,
                             ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional
    public Review addReview(Review review) {

        List<ReviewCreateCheck> checks = new ArrayList<>(Arrays.asList(
                new ReviewProductIdCreateCheck(productRepository),
                new ReviewUserIdCreateCheck(userRepository),
                new ReviewRevCreateCheck()
        ));

        MainReviewCreateCheck createCheck = new MainReviewCreateCheck(checks);

        if(createCheck.fullCreateCheck(review)){

            reviewRepository.save(review);
            LOGGER.info("Review with user - {} and product - {} - successfully added",review.getUser().getId(),review.getProduct().getId());

            return review;
        }

        LOGGER.warn("Review with user - {} or product - {} not found",review.getUser().getId(),review.getProduct().getId());
        return null;
    }

    @Override
    @Transactional
    public Review updateReview(int id,Review review) {

        Review certainReview = reviewRepository.findById(id);

        List<ReviewUpdateCheck> updateChecks = new ArrayList<>(Arrays.asList(
                new ReviewProductIdUpdateCheck(productRepository),
                new ReviewUserIdUpdateCheck(userRepository),
                new ReviewRevUpdateCheck()
        ));

        MainReviewUpdateCheck updateCheck = new MainReviewUpdateCheck(updateChecks);

        try {
            updateCheck.updateChecks(certainReview, review);
            LOGGER.info("{} - review was successfully updated",id);
            return reviewRepository.findById(id);
        }
        catch (Exception e){
            LOGGER.warn("{} - review was not updated",id);
        }

        return reviewRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteReviewById(int id) {
        Integer userId = reviewRepository.findById(id).getUser().getId();
        Integer productId = reviewRepository.findById(id).getProduct().getId();
        reviewRepository.deleteById(id);
        LOGGER.info("Review with userId - {} and productId - {}  - successfully deleted",userId,productId);
    }

    @Override
    public Review getReviewById(int id) {
        return reviewRepository.findById(id);
    }

}