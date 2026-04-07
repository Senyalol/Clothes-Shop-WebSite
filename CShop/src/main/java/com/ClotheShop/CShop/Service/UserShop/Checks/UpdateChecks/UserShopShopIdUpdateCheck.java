package com.ClotheShop.CShop.Service.UserShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.UserShop;
import com.ClotheShop.CShop.Repository.LocationShopRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserShopShopIdUpdateCheck implements UserShopUpdateCheck {

    private LocationShopRepository shopRepository;
    private static Logger LOGGER = LogManager.getLogger(UserShopShopIdUpdateCheck.class);


    public UserShopShopIdUpdateCheck(LocationShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void updateCheck(UserShop oldData, UserShop newData) {

        if(newData != null && newData.getShop() != null && shopRepository.findById(newData.getShop().getId()).isPresent()){

            Integer oldShopId = oldData.getShop().getId();
            oldData.setShop(newData.getShop());
            LOGGER.info("UserShop ShopId - {} , was updated", oldShopId);
        }

    }

}