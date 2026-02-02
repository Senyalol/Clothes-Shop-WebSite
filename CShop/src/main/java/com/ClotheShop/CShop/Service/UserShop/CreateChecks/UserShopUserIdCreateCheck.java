package com.ClotheShop.CShop.Service.UserShop.CreateChecks;

import com.ClotheShop.CShop.Entity.UserShop;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserShopUserIdCreateCheck implements UserShopCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(UserShopUserIdCreateCheck.class);
    private UserRepository userRepository;

    public UserShopUserIdCreateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(UserShop userShop) {

        boolean result = true;

        if(userShop == null || userShop.getUser() == null ||
                !userRepository.findById(userShop.getUser().getId()).isPresent()){

            LOGGER.warn("Incorrect userID");
            result = false;

        }

        return result;
    }

}