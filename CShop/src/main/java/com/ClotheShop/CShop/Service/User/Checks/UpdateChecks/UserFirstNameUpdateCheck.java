package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserFirstNameUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserFirstNameUpdateCheck.class);

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getFirstName() != null && !newData.getFirstName().isEmpty()){

            String oldFirstName = certainUser.getFirstName();
            certainUser.setFirstName(newData.getFirstName());
            LOGGER.info("{} User FirstName was updated from {} to {}",certainUser.getId(),oldFirstName,newData.getFirstName());
        }

    }

}