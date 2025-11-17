package com.ClotheShop.CShop.Service.Review.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Review;

import java.util.List;

public class MainReviewUpdateCheck {

    private List<ReviewUpdateCheck> checks;

    public MainReviewUpdateCheck(List<ReviewUpdateCheck> checks) {
        this.checks = checks;
    }

    public void updateChecks(Review oldReview, Review newReview) {

        for(ReviewUpdateCheck check : checks) {
            check.check(oldReview, newReview);
        }

    }

}
