package com.ClotheShop.CShop.Service.UserShop.UpdateChecks;

import com.ClotheShop.CShop.Entity.UserShop;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserShopUserIdUpdateCheck implements UserShopUpdateCheck {

    private UserRepository userRepository;
    private static Logger LOGGER = LogManager.getLogger(UserShopUserIdUpdateCheck.class);

    public UserShopUserIdUpdateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateCheck(UserShop oldData, UserShop newData) {

        if(newData != null && newData.getUser() != null && userRepository.findById(newData.getUser().getId()).isPresent()){

            Integer oldUser = oldData.getUser().getId();
            oldData.setUser(newData.getUser());
            LOGGER.info("UserShop User - {} , was updated", oldUser);

        }

    }

}