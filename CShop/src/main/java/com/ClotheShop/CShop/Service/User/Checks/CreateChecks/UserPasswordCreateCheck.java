package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserPasswordCreateCheck implements UserCreateCheck {

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getPassword() == null || user.getPassword().isEmpty()){

            result = false;

        }

        return result;
    }

}
