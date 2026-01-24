package com.ClotheShop.CShop.Service.LocationShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopRatingUpdateCheck implements ShopUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ShopRatingUpdateCheck.class);


    @Override
    public void updateCheck(Locationshop newData, Locationshop oldData) {

        if(newData.getRating() != null && newData.getRating() > 0 && newData.getRating() <= 5){
            oldData.setRating(newData.getRating());
            LOGGER.info("Shop {} , rating {}",oldData.getId(),newData.getRating());
        }

    }

}