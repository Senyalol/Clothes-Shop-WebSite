package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserBalanceUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserBalanceUpdateCheck.class);

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getBalance() != null
        && newData.getBalance() >= 0){

            Double oldBalance = certainUser.getBalance();
            certainUser.setBalance(newData.getBalance());
            LOGGER.info("{} User balance was updated from {} to {}",certainUser.getId(),oldBalance,newData.getBalance());
        }

    }

}