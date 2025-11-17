package com.ClotheShop.CShop.Service.Review.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Review;

public interface ReviewUpdateCheck {

    void check(Review oldReview, Review newReview);

}