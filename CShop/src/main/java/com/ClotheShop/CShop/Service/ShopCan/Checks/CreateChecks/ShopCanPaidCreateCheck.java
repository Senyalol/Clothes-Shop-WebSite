package com.ClotheShop.CShop.Service.ShopCan.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCanPaidCreateCheck implements ShopCanCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopCanPaidCreateCheck.class);

    @Override
    public boolean check(ShopCan shopCan) {

        boolean result = true;

        if(shopCan == null || shopCan.getPaid() == null){

            result = false;
            LOGGER.warn("Error adding to cart");

        }

        return result;
    }

}