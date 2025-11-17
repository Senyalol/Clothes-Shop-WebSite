package com.ClotheShop.CShop.Service.Review.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewProductIdCreateCheck implements ReviewCreateCheck {

    private ProductRepository productRepository;
    private static final Logger LOGGER = LogManager.getLogger(ReviewProductIdCreateCheck.class);

    public ReviewProductIdCreateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean check(Review review) {

        boolean result = true;

        if(review == null || review.getProduct().getId() == null ||
        review.getProduct().getId() == 0 || productRepository.findById(review.getProduct().getId()).isEmpty()){
            result = false;
            LOGGER.warn("Review product id is null or empty {}", review.getProduct().getId());
        }

        return result;
    }

}