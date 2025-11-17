package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLoginCreateCheck implements UserCreateCheck {

    private UserRepository userRepository;
    private static Logger LOGGER = LogManager.getLogger(UserLoginCreateCheck.class);

    public UserLoginCreateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(User user) {

        boolean result = true;
        if(user == null || user.getLogin() == null || userRepository.existsByLogin(user.getLogin())){

            result = false;
            LOGGER.warn("Login - {} for new user - is not correct or already exists",user.getLogin());
        }

        return result;
    }

}