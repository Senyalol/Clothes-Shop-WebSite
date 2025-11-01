package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLastNameUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserLastNameUpdateCheck.class);

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getLastName() != null && !newData.getLastName().isEmpty()){

            String oldLastName = certainUser.getLastName();
            certainUser.setLastName(newData.getLastName());
            LOGGER.info("{} User was updated from {} to {}",certainUser.getId(),oldLastName,newData.getLastName());
        }

    }

}