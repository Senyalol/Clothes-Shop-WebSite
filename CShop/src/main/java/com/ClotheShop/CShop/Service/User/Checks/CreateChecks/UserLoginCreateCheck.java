package com.ClotheShop.CShop.Service.User.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;

public class UserLoginCreateCheck implements UserCreateCheck {

    private UserRepository userRepository;

    public UserLoginCreateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(User user) {

        boolean result = true;
        if(user == null || user.getLogin() == null || userRepository.existsByLogin(user.getLogin())){

            result = false;

        }

        return result;
    }

}