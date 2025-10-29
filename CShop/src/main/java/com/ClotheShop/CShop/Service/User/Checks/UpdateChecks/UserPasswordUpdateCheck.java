package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserPasswordUpdateCheck implements UserUpdateCheck{

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getPassword() != null && !newData.getPassword().isEmpty()){

            certainUser.setPassword(newData.getPassword());

        }

    }

}