package com.ClotheShop.CShop.Service.Favorite.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Favorite;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FavoriteUserIdCreateCheck implements FavoriteCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(FavoriteUserIdCreateCheck.class);
    private UserRepository userRepository;

    public FavoriteUserIdCreateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(Favorite favorite) {

        boolean result = true;

        if(favorite == null || favorite.getUser() == null
                || userRepository.findById(favorite.getUser().getId()).isEmpty()) {

            result = false;
            LOGGER.warn("Error adding to favorites - user - {} , not found",favorite.getUser().getId());
        }

        return result;
    }
}