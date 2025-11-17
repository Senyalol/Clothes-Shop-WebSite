package com.ClotheShop.CShop.Service.Review.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewRevUpdateCheck implements ReviewUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ReviewRevUpdateCheck.class);

    @Override
    public void check(Review oldReview, Review newReview) {

        if(newReview != null && newReview.getReview() != null){

            String oldRev = oldReview.getReview();
            oldReview.setReview(newReview.getReview());
            String newRev = oldReview.getReview();
            LOGGER.info("{} - Review was updated , old Review - {} , new Review",oldReview.getId(),oldRev,newRev);

        }

    }

}