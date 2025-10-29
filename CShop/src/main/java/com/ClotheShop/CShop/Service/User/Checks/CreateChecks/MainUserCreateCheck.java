package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;

import java.util.List;

public class MainUserCreateCheck {

    private List<UserCreateCheck> checks;

    public MainUserCreateCheck(List<UserCreateCheck> checks) {
        this.checks = checks;
    }

    public boolean check(User user) {

        boolean result = true;

        for(UserCreateCheck check : checks) {

            if(!check.check(user)) {
                result = false;
            }

        }

        return result;
    }

}
