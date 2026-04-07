package com.ClotheShop.CShop.Service.UserShop.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.UserShop;

public interface UserShopUpdateCheck {

    void updateCheck(UserShop oldData, UserShop newData);

}