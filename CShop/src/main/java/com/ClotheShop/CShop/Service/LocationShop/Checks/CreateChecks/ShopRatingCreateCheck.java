package com.ClotheShop.CShop.Service.LocationShop.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopRatingCreateCheck implements ShopCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopRatingCreateCheck.class);


    @Override
    public boolean check(Locationshop shop) {

        boolean result = true;

        if(shop.getRating() == null || shop.getRating() < 0 || shop.getRating() > 5) {
            result = false;
            LOGGER.warn("Incorrect shop rating value");
        }

        return result;
    }

}