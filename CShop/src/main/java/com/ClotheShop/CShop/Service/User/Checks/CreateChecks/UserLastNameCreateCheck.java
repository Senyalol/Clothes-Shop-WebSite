package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLastNameCreateCheck implements UserCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(UserLastNameCreateCheck.class);

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getLastName() == null || user.getLastName().isEmpty()){

            result = false;
            LOGGER.warn("LastName is null or empty for new User");
        }

        return result;
    }
}
