package com.ClotheShop.CShop.Service.ShopCan.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;

import java.util.List;

public class MainShopCanCreateCheck {

    private List<ShopCanCreateCheck> checks;

    public MainShopCanCreateCheck(List<ShopCanCreateCheck> checks) {
        this.checks = checks;
    }

    public boolean createChecks(ShopCan shopCan){

        boolean result = true;

        for(ShopCanCreateCheck check : checks){

            if(!check.check(shopCan)){
                result = false;
            }

        }

        return result;
    }

}