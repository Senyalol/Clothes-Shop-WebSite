package com.ClotheShop.CShop.Service.LocationShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopLocationUpdateCheck implements ShopUpdateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopLocationUpdateCheck.class);


    @Override
    public void updateCheck(Locationshop newData, Locationshop oldData) {

        if(newData.getLocation() != null && !newData.getLocation().isEmpty()){
            oldData.setLocation(newData.getLocation());
            LOGGER.info("Shop {} , location {}",oldData.getId(),newData.getLocation());
        }

    }

}