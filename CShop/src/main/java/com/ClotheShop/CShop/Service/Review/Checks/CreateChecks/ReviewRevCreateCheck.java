package com.ClotheShop.CShop.Service.Review.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewRevCreateCheck implements ReviewCreateCheck {

    private static final Logger LOGGER = LogManager.getLogger(ReviewRevCreateCheck.class);

    @Override
    public boolean check(Review review) {

        boolean result = true;

        if(review == null || review.getReview() == null || review.getReview().isEmpty()){
            result = false;
            LOGGER.warn("Review is null or empty");
        }

        return result;
    }

}