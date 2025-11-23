package com.ClotheShop.CShop.Service.ShopCan.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCanPaidUpdateCheck implements ShopCanUpdateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopCanPaidUpdateCheck.class);

    @Override
    public void check(ShopCan oldShop, ShopCan newShop) {

        if(newShop != null && newShop.getPaid() != null){

            Boolean oldPaidData = oldShop.getPaid();
            oldShop.setPaid(newShop.getPaid());
            LOGGER.info("{} - Shop Can paid was updated from - {} , to - {}",oldShop.getId(),oldPaidData,oldShop.getPaid());

        }

    }

}