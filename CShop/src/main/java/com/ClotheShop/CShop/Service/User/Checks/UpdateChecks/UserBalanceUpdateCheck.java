package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserBalanceUpdateCheck implements UserUpdateCheck{

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getBalance() != null
        && newData.getBalance() >= 0){

            certainUser.setBalance(newData.getBalance());

        }

    }

}