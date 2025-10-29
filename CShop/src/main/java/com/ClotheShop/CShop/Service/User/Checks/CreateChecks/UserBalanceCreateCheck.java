package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserBalanceCreateCheck implements UserCreateCheck {

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getBalance() == null || user.getBalance() < 0){
            result = false;
        }

        return result;
    }

}
