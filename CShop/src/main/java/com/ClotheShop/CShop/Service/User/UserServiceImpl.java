package com.ClotheShop.CShop.Service.User;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Service.User.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.User.Checks.UpdateChecks.*;
import com.ClotheShop.CShop.Service.User.Checks.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User addUser(User user) {

        List<UserCreateCheck> cChecks = Arrays.asList(
            new UserLoginCreateCheck(userRepository),
            new UserPasswordCreateCheck(),
            new UserFirstNameCreateCheck(),
            new UserLastNameCreateCheck(),
            new UserBalanceCreateCheck()
        );

        MainUserCreateCheck createChecks = new MainUserCreateCheck(cChecks);

        if(createChecks.check(user)){

            user.setBalance(0.0);

            if(user.getSecretKey() != null && user.getSecretKey().equals("yrF%B$~#IO")){
               user.setRole(UserRoles.ADMIN.name());
            }
            else{
                user.setRole(UserRoles.USER.name());
            }

            userRepository.save(user);

            return user;
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public User updateUserById(int id, User user) {

        User certainUser = userRepository.findById(id).get();

        List<UserUpdateCheck> updateChecks = Arrays.asList(
                new UserLoginUpdateCheck(userRepository),
                new UserPasswordUpdateCheck(),
                new UserFirstNameUpdateCheck(),
                new UserLastNameUpdateCheck(),
                new UserBalanceUpdateCheck(),
                new UserSecretKeyUpdateCheck()
        );

        MainUserUpdateCheck userUpdateCheck = new MainUserUpdateCheck(updateChecks);

        try{

            userUpdateCheck.applyChecks(certainUser, user);

        }
        catch(Exception e){
            throw new RuntimeException();
        }


        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

}