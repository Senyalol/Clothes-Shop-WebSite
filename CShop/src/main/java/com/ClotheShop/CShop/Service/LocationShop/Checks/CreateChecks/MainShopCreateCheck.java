package com.ClotheShop.CShop.Service.LocationShop.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;

import java.util.List;

public class MainShopCreateCheck {

    private List<ShopCreateCheck> shopCreateChecks;

    public MainShopCreateCheck(List<ShopCreateCheck> shopCreateChecks) {
        this.shopCreateChecks = shopCreateChecks;
    }

    public boolean createCheck(Locationshop locationshop) {

        boolean result = true;

        for(ShopCreateCheck shopCreateCheck : shopCreateChecks) {

            if(!shopCreateCheck.check(locationshop)) {
                result = false;
            }

        }

        return result;
    }

}