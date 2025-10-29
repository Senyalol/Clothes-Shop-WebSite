package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Service.User.Checks.UserRoles;

public class UserSecretKeyUpdateCheck implements UserUpdateCheck{

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getSecretKey() != null && !newData.getSecretKey().isEmpty()){

            if(newData.getSecretKey().equals("yrF%B$~#IO")){
                certainUser.setRole(UserRoles.ADMIN.name());
            }
            else{
                certainUser.setRole(UserRoles.USER.name());
            }

        }

    }

}