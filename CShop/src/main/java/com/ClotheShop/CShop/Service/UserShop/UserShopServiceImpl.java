package com.ClotheShop.CShop.Service.UserShop;

import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Entity.UserShop;
import com.ClotheShop.CShop.Repository.LocationShopRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Repository.UserShopRepository;
import com.ClotheShop.CShop.Security.JWTService;
import com.ClotheShop.CShop.Service.UserShop.CreateChecks.MainUserShopCreateCheck;
import com.ClotheShop.CShop.Service.UserShop.CreateChecks.UserShopCreateCheck;
import com.ClotheShop.CShop.Service.UserShop.CreateChecks.UserShopShopIdCreateCheck;
import com.ClotheShop.CShop.Service.UserShop.UpdateChecks.MainUserShopUpdateCheck;
import com.ClotheShop.CShop.Service.UserShop.UpdateChecks.UserShopShopIdUpdateCheck;
import com.ClotheShop.CShop.Service.UserShop.UpdateChecks.UserShopUpdateCheck;
import com.ClotheShop.CShop.Service.UserShop.UpdateChecks.UserShopUserIdUpdateCheck;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@JsonSerialize
public class UserShopServiceImpl implements UserShopService{

    private static Logger LOGGER = LogManager.getLogger(UserShopServiceImpl.class);

    private final UserShopRepository userShopRepository;
    private final UserRepository userRepository;
    private final LocationShopRepository locationShopRepository;
    private final JWTService jwtService;

    @Autowired
    public UserShopServiceImpl(UserShopRepository userShopRepository, UserRepository userRepository, LocationShopRepository locationShopRepository, JWTService jwtService) {
        this.userShopRepository = userShopRepository;
        this.userRepository = userRepository;
        this.locationShopRepository = locationShopRepository;
        this.jwtService = jwtService;
    }

    @Override
    public List<UserShop> getUserShops() {
        return userShopRepository.findAll();
    }

    @Transactional
    @Override
    public UserShop createUserShop(UserShop userShop) {

        List<UserShopCreateCheck> createChecks = new ArrayList<>(Arrays.asList(
                new UserShopShopIdCreateCheck(locationShopRepository)
        ));

        MainUserShopCreateCheck mainCreateCheck = new MainUserShopCreateCheck(createChecks);

        if(mainCreateCheck.createCheck(userShop)){

            LOGGER.info("User shop created with User - {} and Shop - {} ",userShop.getUser().getId(),userShop.getShop().getId());
            return userShopRepository.save(userShop);

        }

        else {
            LOGGER.warn("Incorrect User or Shop :  User - {} and Shop - {} ",userShop.getUser().getId(),userShop.getShop().getId());
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteUserShop(int id) {

        userShopRepository.deleteById(id);
        LOGGER.info("Deleting User shop with id - {} ",id);

    }

    @Override
    public void deleteYourShop(int id, String token) {

        User yourself = userRepository.findByLogin(jwtService.getLoginFromToken(token)).get();

        List<UserShop> yourShops = userShopRepository.findByUserId(yourself.getId());

        if(yourShops.contains(userShopRepository.findById(id).get())){
            userShopRepository.deleteById(id);
            LOGGER.info("Deleting User shop with id - {} ",id);
        }

    }

    @Transactional
    @Override
    public UserShop updateUserShopById(int id, UserShop newData) {

        UserShop userShop = userShopRepository.findById(id).get();

        List<UserShopUpdateCheck> updateChecks = new ArrayList<>(Arrays.asList(
           new UserShopUserIdUpdateCheck(userRepository),
           new UserShopShopIdUpdateCheck(locationShopRepository)
        ));

        MainUserShopUpdateCheck updateCheck = new MainUserShopUpdateCheck(updateChecks);

        updateCheck.updateChecks(userShop,newData);

        return userShop;
    }

    @Transactional
    @Override
    public UserShop updateYourShop(int id, String token, UserShop newData) {

        UserShop userShop = userShopRepository.findById(id).get();
        User yourself = userRepository.findByLogin(jwtService.getLoginFromToken(token)).get();

        List<UserShopUpdateCheck> updateChecks = new ArrayList<>(Arrays.asList(
                new UserShopShopIdUpdateCheck(locationShopRepository)
        ));

        MainUserShopUpdateCheck updateCheck = new MainUserShopUpdateCheck(updateChecks);

        List<UserShop> allUserShops = userShopRepository.findByUserId(yourself.getId());


        if(allUserShops.contains(userShop)){
            updateCheck.updateChecks(userShop,newData);
            return userShopRepository.findById(id).get();
        }

        return null;
    }

    @Override
    public List<UserShop> getShopsByUser(String token) {

        Integer yourselfID = userRepository.findByLogin(jwtService.getLoginFromToken(token)).get().getId();

        return userShopRepository.findByUserId(yourselfID);
    }

}