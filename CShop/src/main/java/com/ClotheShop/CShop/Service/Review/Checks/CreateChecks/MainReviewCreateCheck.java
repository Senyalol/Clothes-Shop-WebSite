package com.ClotheShop.CShop.Service.Review.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Review;

import java.util.List;

public class MainReviewCreateCheck{

    private List<ReviewCreateCheck> checks;

    public MainReviewCreateCheck(List<ReviewCreateCheck> checks){
        this.checks = checks;
    }

    public boolean fullCreateCheck(Review review){

        boolean result = true;

        for(ReviewCreateCheck check : checks){

            if(!check.check(review)){
                result = false;
            }

        }

        return result;
    }

}