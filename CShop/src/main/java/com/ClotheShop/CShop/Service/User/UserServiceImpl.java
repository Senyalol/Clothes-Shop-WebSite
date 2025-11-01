package com.ClotheShop.CShop.Service.User;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Service.User.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.User.Checks.UpdateChecks.*;
import com.ClotheShop.CShop.Service.User.Checks.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

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
            LOGGER.info("User : {} successfully added", user.getLogin());
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
        String oldLogin = certainUser.getLogin();
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
            LOGGER.info("User : {} successfully updated", oldLogin);
        }
        catch(Exception e){
            throw new RuntimeException();
        }


        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteUserById(int id) {
        String certainLogin = userRepository.findById(id).get().getLogin();
        userRepository.deleteById(id);
        LOGGER.info("User: {} successfully deleted",certainLogin);
    }

}