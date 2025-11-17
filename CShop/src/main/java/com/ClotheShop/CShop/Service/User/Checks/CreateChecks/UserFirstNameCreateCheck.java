package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserFirstNameCreateCheck implements UserCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(UserFirstNameCreateCheck.class);

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getFirstName() == null || user.getFirstName().isEmpty()){
            result = false;
            LOGGER.warn("FirstName is null or empty for new User");
        }

        return result;
    }

}