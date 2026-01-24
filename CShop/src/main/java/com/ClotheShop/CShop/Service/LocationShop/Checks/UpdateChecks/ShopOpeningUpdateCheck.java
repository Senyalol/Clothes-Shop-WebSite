package com.ClotheShop.CShop.Service.LocationShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopOpeningUpdateCheck implements ShopUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ShopOpeningUpdateCheck.class);

    @Override
    public void updateCheck(Locationshop newData, Locationshop oldData) {

        if(newData.getOpening() != null){

            oldData.setOpening(newData.getOpening());
            LOGGER.info("Shop {} , opening {}",oldData.getId(),newData.getOpening());
        }

    }

}