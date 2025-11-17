package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserPasswordCreateCheck implements UserCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(UserPasswordCreateCheck.class);

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getPassword() == null || user.getPassword().isEmpty()){

            result = false;
            LOGGER.warn("Password for new user - is not correct");
        }

        return result;
    }

}
