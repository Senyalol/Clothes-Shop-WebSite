package com.ClotheShop.CShop.Service.Review.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Review;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReviewUserIdCreateCheck implements ReviewCreateCheck {

    private UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(ReviewUserIdCreateCheck.class);

    public ReviewUserIdCreateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(Review review) {

        boolean result = true;

        if(review == null || review.getUser().getId() == null
    || review.getUser().getId() == 0 || userRepository.findById(review.getUser().getId()).isEmpty()) {
            result = false;
            LOGGER.warn("User id is null or empty {}", review.getUser().getId());
        }

        return result;
    }

}