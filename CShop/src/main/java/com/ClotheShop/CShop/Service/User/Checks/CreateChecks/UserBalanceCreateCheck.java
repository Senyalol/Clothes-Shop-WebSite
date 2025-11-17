package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserBalanceCreateCheck implements UserCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(UserBalanceCreateCheck.class);

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getBalance() == null || user.getBalance() < 0){
            result = false;
            LOGGER.warn("Incorrect value for balance - {}", user.getBalance());
        }

        return result;
    }

}
