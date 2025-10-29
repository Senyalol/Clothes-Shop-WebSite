package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserFirstNameUpdateCheck implements UserUpdateCheck{

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getFirstName() != null && !newData.getFirstName().isEmpty()){

            certainUser.setFirstName(newData.getFirstName());

        }

    }

}