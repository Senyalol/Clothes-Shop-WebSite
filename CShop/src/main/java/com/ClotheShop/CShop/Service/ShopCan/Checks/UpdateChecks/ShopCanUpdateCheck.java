package com.ClotheShop.CShop.Service.ShopCan.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;

public interface ShopCanUpdateCheck {

    void check(ShopCan oldShop, ShopCan newShop);

}