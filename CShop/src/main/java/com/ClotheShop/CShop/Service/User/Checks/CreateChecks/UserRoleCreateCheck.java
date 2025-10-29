package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Service.User.Checks.UserRoles;

public class UserRoleCreateCheck implements UserCreateCheck {

    @Override
    public boolean check(User user) {

        boolean result = true;

        if(user == null || user.getRole() == null || user.getRole().isEmpty()){
            result = false;
        }
        else if (!user.getRole().equals(UserRoles.USER.name())){

            if(!user.getRole().equals(UserRoles.ADMIN.name())){
                result = false;
            }

        }

        return result;
    }

}
