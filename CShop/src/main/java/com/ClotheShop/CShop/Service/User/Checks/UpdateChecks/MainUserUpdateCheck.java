package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;

import java.util.List;

public class MainUserUpdateCheck {

    private List<UserUpdateCheck> updateChecks;

    public MainUserUpdateCheck(List<UserUpdateCheck> updateChecks) {
        this.updateChecks = updateChecks;
    }

    public void applyChecks(User certainUser, User newData){

        for(UserUpdateCheck check : updateChecks){
            check.upCheck(certainUser, newData);
        }

    }

}
