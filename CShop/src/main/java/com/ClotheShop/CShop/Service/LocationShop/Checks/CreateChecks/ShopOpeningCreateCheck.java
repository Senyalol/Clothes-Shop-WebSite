package com.ClotheShop.CShop.Service.LocationShop.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopOpeningCreateCheck implements ShopCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopOpeningCreateCheck.class);


    @Override
    public boolean check(Locationshop shop) {

        boolean result = true;

        if(shop.getOpening() == null){
            result = false;
            LOGGER.warn("Shop opening is null");
        }

        return result;
    }

}