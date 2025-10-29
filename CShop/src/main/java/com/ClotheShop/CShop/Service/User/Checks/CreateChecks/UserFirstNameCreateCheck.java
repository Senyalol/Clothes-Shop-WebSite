package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserFirstNameCreateCheck implements UserCreateCheck {

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getFirstName() == null || user.getFirstName().isEmpty()){
            result = false;
        }

        return result;
    }

}