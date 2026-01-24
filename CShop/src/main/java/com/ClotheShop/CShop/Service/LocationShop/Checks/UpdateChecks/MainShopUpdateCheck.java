package com.ClotheShop.CShop.Service.LocationShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;

import java.util.List;

public class MainShopUpdateCheck {

    private List<ShopUpdateCheck> updateChecks;

    public MainShopUpdateCheck(List<ShopUpdateCheck> updateChecks) {
        this.updateChecks = updateChecks;
    }

    public void updateChecks(Locationshop newData,Locationshop oldData) {

        for(ShopUpdateCheck updateCheck : updateChecks) {

            updateCheck.updateCheck(newData, oldData);

        }

    }

}