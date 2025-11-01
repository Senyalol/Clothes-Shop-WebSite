package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Service.User.Checks.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserSecretKeyUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserSecretKeyUpdateCheck.class);

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getSecretKey() != null && !newData.getSecretKey().isEmpty()){

            String oldRole = "";
            if(newData.getSecretKey().equals("yrF%B$~#IO")){
                oldRole = certainUser.getRole();
                certainUser.setRole(UserRoles.ADMIN.name());
                LOGGER.info("{} User role was updated from {} to {}",certainUser.getId(),oldRole,UserRoles.ADMIN.name());
            }
            else{
                oldRole = certainUser.getRole();
                certainUser.setRole(UserRoles.USER.name());
                LOGGER.info("{} User role was updated from {} to {}",certainUser.getId(),oldRole,UserRoles.USER.name());
            }

        }

    }

}