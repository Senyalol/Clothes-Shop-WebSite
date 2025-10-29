package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;

public class UserLoginUpdateCheck implements UserUpdateCheck{

    private UserRepository userRepository;

    public UserLoginUpdateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getLogin() != null
                && userRepository.findByLogin(newData.getLogin()).isEmpty() && !newData.getLogin().isEmpty()) {

            certainUser.setLogin(newData.getLogin());

        }

    }

}
