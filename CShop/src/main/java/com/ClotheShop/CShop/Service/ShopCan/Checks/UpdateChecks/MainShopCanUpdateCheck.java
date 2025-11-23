package com.ClotheShop.CShop.Service.ShopCan.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;

import java.util.List;

public class MainShopCanUpdateCheck {

    private List<ShopCanUpdateCheck> checks;

    public MainShopCanUpdateCheck(List<ShopCanUpdateCheck> checks) {
        this.checks = checks;
    }

    public void updateChecks(ShopCan oldShop, ShopCan newShop) {

        for(ShopCanUpdateCheck check : checks) {

            check.check(oldShop, newShop);

        }

    }

}