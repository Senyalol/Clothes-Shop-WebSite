package com.ClotheShop.CShop.Service.LocationShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Locationshop;

public interface ShopUpdateCheck {

    void updateCheck(Locationshop newData, Locationshop oldData);

}