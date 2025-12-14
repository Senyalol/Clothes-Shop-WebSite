package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Security.SDTO.VerifyChangeDTO;
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

    @Override
    public void upUserCheck(User certainUser, VerifyChangeDTO verifyChangeDTO) {

        if(verifyChangeDTO != null && verifyChangeDTO.getBalance() != null
                && verifyChangeDTO.getBalance() >= 0){

            Double oldBalance = certainUser.getBalance();
            certainUser.setBalance(verifyChangeDTO.getBalance());
            LOGGER.info("{} User balance was updated from {} to {}",certainUser.getId(),oldBalance,verifyChangeDTO.getBalance());
        }

    }

}