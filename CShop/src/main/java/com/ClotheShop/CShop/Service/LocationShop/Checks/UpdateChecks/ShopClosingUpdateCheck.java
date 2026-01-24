package com.ClotheShop.CShop.Service.LocationShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopClosingUpdateCheck implements ShopUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ShopClosingUpdateCheck.class);


    @Override
    public void updateCheck(Locationshop newData, Locationshop oldData) {

        if(newData.getClosing() != null){
            oldData.setClosing(newData.getClosing());
            LOGGER.info("shop {} Closing: {} " , oldData.getId(),newData.getClosing());
        }

    }

}