package com.ClotheShop.CShop.Service.LocationShop;

import com.ClotheShop.CShop.Entity.Locationshop;
import com.ClotheShop.CShop.Repository.LocationShopRepository;
import com.ClotheShop.CShop.Service.LocationShop.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.LocationShop.Checks.UpdateChecks.*;
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
public class LocationShopServiceImpl implements LocationShopService{

    private final LocationShopRepository locationShopRepository;
    private static Logger LOGGER = LogManager.getLogger(LocationShopServiceImpl.class);


    @Autowired
    public LocationShopServiceImpl(LocationShopRepository locationShopRepository) {
        this.locationShopRepository = locationShopRepository;
    }

    @Override
    public List<Locationshop> getAllShops() {
        return locationShopRepository.findAll();
    }

    @Transactional
    @Override
    public Locationshop createShop(Locationshop shop) {

        List<ShopCreateCheck> createChecks = new ArrayList<>(Arrays.asList(
                new ShopLocationCreateCheck(),
                new ShopRatingCreateCheck(),
                new ShopOpeningCreateCheck(),
                new ShopClosingCreateCheck()
        ));

        MainShopCreateCheck createBoolean = new MainShopCreateCheck(createChecks);

        if(createBoolean.createCheck(shop)){

            locationShopRepository.save(shop);
            LOGGER.info("Shop with id - {} , successfully created", shop.getId());
            return shop;

        }

        else{
            return null;
        }

    }

    @Transactional
    @Override
    public Locationshop updateShop(int id, Locationshop shop) {

        List<ShopUpdateCheck> updateChecks = new ArrayList<>(Arrays.asList(
            new ShopLocationUpdateCheck(),
            new ShopRatingUpdateCheck(),
            new ShopOpeningUpdateCheck(),
            new ShopClosingUpdateCheck()
        ));

        MainShopUpdateCheck updateBoolean = new MainShopUpdateCheck(updateChecks);
        Locationshop oldShop = locationShopRepository.findById(id);

        updateBoolean.updateChecks(shop, oldShop);

        return locationShopRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteShop(int id) {
        locationShopRepository.deleteById(id);
    }

}