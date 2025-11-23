package com.ClotheShop.CShop.Service.ShopCan.Checks.CreateChecks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ClotheShop.CShop.Entity.ShopCan;

public class ShopCanCostCreateCheck implements ShopCanCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopCanCostCreateCheck.class);

    @Override
    public boolean check(ShopCan shopCan) {

        boolean result = true;

        if(shopCan == null || shopCan.getCost() == null || shopCan.getCost() < 0){

            result = false;
            LOGGER.warn("Error adding to cart");

        }

        return result;
    }

}