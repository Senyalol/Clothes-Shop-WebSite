package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.DTO.UserDTO.VerifyChangeDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserPasswordUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserPasswordUpdateCheck.class);
    private final PasswordEncoder passwordEncoder;

    public UserPasswordUpdateCheck(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getPassword() != null && !newData.getPassword().isEmpty()){

            certainUser.setPassword(passwordEncoder.encode(newData.getPassword()));
            LOGGER.info("{} User password was updated",certainUser.getId());

        }

    }

    @Override
    public void upUserCheck(User certainUser, VerifyChangeDTO verifyChangeDTO) {
        if(verifyChangeDTO != null && verifyChangeDTO.getPassword() != null && !verifyChangeDTO.getPassword().isEmpty()){

            certainUser.setPassword(passwordEncoder.encode(verifyChangeDTO.getPassword()));
            LOGGER.info("{} User password was updated",certainUser.getId());

        }
    }

}