package com.ClotheShop.CShop.Service.UserShop.UpdateChecks;

import com.ClotheShop.CShop.Entity.UserShop;

public interface UserShopUpdateCheck {

    void updateCheck(UserShop oldData, UserShop newData);

}