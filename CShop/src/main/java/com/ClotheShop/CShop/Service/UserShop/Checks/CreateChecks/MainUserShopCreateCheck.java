package com.ClotheShop.CShop.Service.UserShop.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.UserShop;

import java.util.List;

public class MainUserShopCreateCheck {

    private List<UserShopCreateCheck> createChecks;

    public MainUserShopCreateCheck(List<UserShopCreateCheck> checks) {
        this.createChecks = checks;
    }

    public boolean createCheck(UserShop newUserShop) {

        boolean result = true;

        for(UserShopCreateCheck check : createChecks) {

            if(!check.check(newUserShop)) {
                result = false;
            }

        }

        return result;
    }

}