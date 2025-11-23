package com.ClotheShop.CShop.Service.ShopCan.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCanCostUpdateCheck implements ShopCanUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ShopCanCostUpdateCheck.class);

    @Override
    public void check(ShopCan oldShop, ShopCan newShop) {

        if(newShop != null && newShop.getCost() != null && newShop.getCost() < 0){

            Double oldCost = oldShop.getCost();
            oldShop.setCost(newShop.getCost());
            LOGGER.info("{} - Shop Can cost was updated from - {} , to - {}",oldShop.getId(),oldCost,oldShop.getCost());

        }

    }

}