package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Security.SDTO.VerifyChangeDTO;

public interface UserUpdateCheck {

    void upCheck(User certainUser, User newData);

    void upUserCheck(User certainUser, VerifyChangeDTO verifyChangeDTO);

}
