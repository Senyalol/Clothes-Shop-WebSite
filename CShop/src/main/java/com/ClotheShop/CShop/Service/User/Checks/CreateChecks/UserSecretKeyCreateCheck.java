package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserSecretKeyCreateCheck implements UserCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(UserSecretKeyCreateCheck.class);

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getSecretKey() == null){
            result = false;
            LOGGER.warn("SecretKey for new User - is not correct");
        }

        return result;
    }

}
