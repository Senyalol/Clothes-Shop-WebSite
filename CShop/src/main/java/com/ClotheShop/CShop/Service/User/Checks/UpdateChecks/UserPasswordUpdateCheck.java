package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserPasswordUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserPasswordUpdateCheck.class);

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getPassword() != null && !newData.getPassword().isEmpty()){

            certainUser.setPassword(newData.getPassword());
            LOGGER.info("{} User password was updated",certainUser.getId());

        }

    }

}