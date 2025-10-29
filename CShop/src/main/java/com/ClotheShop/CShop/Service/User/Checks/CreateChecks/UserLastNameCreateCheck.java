package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserLastNameCreateCheck implements UserCreateCheck {
    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getLastName() == null || user.getLastName().isEmpty()){

            result = false;

        }

        return result;
    }
}
