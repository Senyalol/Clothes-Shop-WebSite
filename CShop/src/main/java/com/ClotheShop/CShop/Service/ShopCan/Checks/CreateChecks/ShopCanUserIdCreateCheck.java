package com.ClotheShop.CShop.Service.ShopCan.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCanUserIdCreateCheck implements ShopCanCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopCanUserIdCreateCheck.class);
    private UserRepository userRepository;

    public ShopCanUserIdCreateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(ShopCan shopCan) {

        boolean result = true;

        if(shopCan == null || shopCan.getUser() == null
                || userRepository.findById(shopCan.getUser().getId()).isEmpty()){

            result = false;
            LOGGER.warn("Error adding to cart, user with id - {} , not found", shopCan.getUser().getId());
        }

        return result;
    }

}