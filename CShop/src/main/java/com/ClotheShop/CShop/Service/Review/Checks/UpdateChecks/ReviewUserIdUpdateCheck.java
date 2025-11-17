package com.ClotheShop.CShop.Service.Review.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewUserIdUpdateCheck implements ReviewUpdateCheck{

    private UserRepository userRepository;
    private static Logger LOGGER = LogManager.getLogger(ReviewUserIdUpdateCheck.class);

    public ReviewUserIdUpdateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void check(Review oldReview, Review newReview) {

        if(newReview != null && newReview.getUser().getId() != null
        && userRepository.findById(newReview.getUser().getId()).isPresent()){

            Integer oldUserId = oldReview.getUser().getId();
            oldReview.setUser(newReview.getUser());
            LOGGER.info("{} - Review was updated, old User - {} , new User - {} ",oldReview.getId(),oldUserId,newReview.getUser().getId());

        }

    }

}