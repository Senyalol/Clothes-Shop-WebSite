package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;

public class UserLastNameUpdateCheck implements UserUpdateCheck{

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getLastName() != null && !newData.getLastName().isEmpty()){

            certainUser.setLastName(newData.getLastName());

        }

    }

}