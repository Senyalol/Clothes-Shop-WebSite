package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLoginUpdateCheck implements UserUpdateCheck{

    private UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(UserLoginUpdateCheck.class);

    public UserLoginUpdateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getLogin() != null
                && userRepository.findByLogin(newData.getLogin()).isEmpty() && !newData.getLogin().isEmpty()) {

            String oldLogin = certainUser.getLogin();
            certainUser.setLogin(newData.getLogin());
            LOGGER.info("{} User login update from {} to {}",certainUser.getId(),oldLogin,newData.getLogin());
        }

    }

}
