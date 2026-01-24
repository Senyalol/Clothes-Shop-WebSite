package com.ClotheShop.CShop.Service.LocationShop.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopClosingCreateCheck implements ShopCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopClosingCreateCheck.class);


    @Override
    public boolean check(Locationshop shop) {

        boolean result = true;

        if(shop.getClosing() == null){
            result = false;
            LOGGER.warn("Closing shop is null");
        }

        return result;
    }

}