package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.DTO.UserDTO.VerifyChangeDTO;
import com.ClotheShop.CShop.Service.User.Checks.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserSecretKeyUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserSecretKeyUpdateCheck.class);
    private String secret;

    public UserSecretKeyUpdateCheck(String secret) {
        this.secret = secret;
    }

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getSecretKey() != null && !newData.getSecretKey().isEmpty()){

            String oldRole = "";
            if(newData.getSecretKey().equals(secret)){
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

    @Override
    public void upUserCheck(User certainUser, VerifyChangeDTO verifyChangeDTO) {

        if(verifyChangeDTO != null && verifyChangeDTO.getSecretKey() != null && !verifyChangeDTO.getSecretKey().isEmpty()){

            String oldRole = "";
            if(verifyChangeDTO.getSecretKey().equals(secret)){
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