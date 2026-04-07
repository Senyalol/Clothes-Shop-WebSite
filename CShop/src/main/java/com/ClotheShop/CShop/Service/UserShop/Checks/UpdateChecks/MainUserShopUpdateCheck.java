package com.ClotheShop.CShop.Service.UserShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.UserShop;

import java.util.List;

public class MainUserShopUpdateCheck {

    private List<UserShopUpdateCheck> updateChecks;

    public MainUserShopUpdateCheck(List<UserShopUpdateCheck> checks) {
        this.updateChecks = checks;
    }

    public void updateChecks(UserShop oldData, UserShop newData) {

        for (UserShopUpdateCheck check : updateChecks) {

            check.updateCheck(oldData, newData);

        }

    }

}