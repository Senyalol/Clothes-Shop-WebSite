package com.ClotheShop.CShop.Service.UserShop.CreateChecks;

import com.ClotheShop.CShop.Entity.UserShop;
import com.ClotheShop.CShop.Repository.LocationShopRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserShopShopIdCreateCheck implements UserShopCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(UserShopShopIdCreateCheck.class);
    private LocationShopRepository shopRepository;

    public UserShopShopIdCreateCheck(LocationShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public boolean check(UserShop userShop) {

        boolean result = true;

        if(userShop == null || userShop.getShop() == null
                || !shopRepository.findById(userShop.getShop().getId()).isPresent()){


            result = false;
            LOGGER.warn("Incorrect ShopId ");
        }

        return result;
    }

}