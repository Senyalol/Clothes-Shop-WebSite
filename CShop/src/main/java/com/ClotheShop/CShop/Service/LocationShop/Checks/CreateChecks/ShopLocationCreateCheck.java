package com.ClotheShop.CShop.Service.LocationShop.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopLocationCreateCheck implements ShopCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopLocationCreateCheck.class);


    @Override
    public boolean check(Locationshop shop) {

        boolean result = true;

        if(shop.getLocation().isEmpty() || shop.getLocation() == null){
            result = false;
            LOGGER.warn("Location shop is empty");
        }

        return result;
    }

}