package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserSecretKeyCreateCheck implements UserCreateCheck {

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getSecretKey() == null){
            result = false;
        }

        return result;
    }

}
