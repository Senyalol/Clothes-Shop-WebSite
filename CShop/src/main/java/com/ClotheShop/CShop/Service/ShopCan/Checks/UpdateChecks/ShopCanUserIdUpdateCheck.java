package com.ClotheShop.CShop.Service.ShopCan.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCanUserIdUpdateCheck implements ShopCanUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ShopCanUserIdUpdateCheck.class);
    private UserRepository userRepository;

    public ShopCanUserIdUpdateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void check(ShopCan oldShop, ShopCan newShop) {

        if(newShop != null && newShop.getUser().getId() != null
                && userRepository.findById(newShop.getUser().getId()).isPresent()){

            Integer oldUserId = oldShop.getUser().getId();
            oldShop.setUser(newShop.getUser());
            LOGGER.info("{} - Shop Can user was updated from - {} , to - {}",oldShop.getId(),oldUserId,oldShop.getUser().getId());
        }

    }

}