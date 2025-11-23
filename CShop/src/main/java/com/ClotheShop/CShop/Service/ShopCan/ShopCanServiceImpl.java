package com.ClotheShop.CShop.Service.ShopCan;

import com.ClotheShop.CShop.Entity.ShopCan;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.ShopCanRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Service.ShopCan.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.ShopCan.Checks.UpdateChecks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShopCanServiceImpl implements ShopCanService{

    private static Logger LOGGER = LogManager.getLogger(ShopCanServiceImpl.class);
    private final ShopCanRepository shopCanRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShopCanServiceImpl(ShopCanRepository shopCanRepository,UserRepository userRepository,ProductRepository productRepository) {
        this.shopCanRepository = shopCanRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ShopCan> getAllShopCan() {
        return shopCanRepository.findAll();
    }

    @Override
    public List<ShopCan> getAllProductsByUserId(int userId) {
        return shopCanRepository.findByUserId(userId);
    }

    //Расчет общей стоимости товаров в корзине для пользователя
//    private Double totalCostCalculation(User user){
//
//        List<ShopCan> allProductsUser = shopCanRepository.findByUserId(user.getId());
//
//        Double result = 0.0;
//
//        for(ShopCan product : allProductsUser){
//
//        //    result += product.
//
//        }
//
//    }

    @Override
    public ShopCan getShopCanById(int id) {
        return shopCanRepository.findById(id).get();
    }

    @Transactional
    @Override
    public ShopCan addToShopCan(ShopCan shopCan) {

        List<ShopCanCreateCheck> checks = new ArrayList<>(Arrays.asList(
                new ShopCanUserIdCreateCheck(userRepository),
                new ShopCanProductIdCreateCheck(productRepository),
                new ShopCanCostCreateCheck(),
                new ShopCanPaidCreateCheck()
        ));

        MainShopCanCreateCheck createCheck = new MainShopCanCreateCheck(checks);

        try{

            if(createCheck.createChecks(shopCan)){

                shopCanRepository.save(shopCan);
                LOGGER.info("Product - {} added at Shop can for user - {}",shopCan.getProduct().getId(),shopCan.getUser().getId());
                return shopCan;
            }

            //LOGGER.info();

        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getCause().toString());
        }


        return null;
    }

    @Transactional
    @Override
    public ShopCan updateShopCan(int id,ShopCan shopCan) {

        ShopCan oldShop = shopCanRepository.findById(id).get();

        List<ShopCanUpdateCheck> checks = new ArrayList<>(Arrays.asList(
            new ShopCanUserIdUpdateCheck(userRepository),
            new ShopCanProductIdUpdateCheck(productRepository),
            new ShopCanCostUpdateCheck(),
            new ShopCanPaidUpdateCheck()
        ));

        MainShopCanUpdateCheck updateCheck = new MainShopCanUpdateCheck(checks);

        try{

            updateCheck.updateChecks(oldShop,shopCan);
            LOGGER.info("{} - Shop can was updated",id);
            return shopCanRepository.findById(id).get();
        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getCause().toString());
        }

    }

    @Transactional
    @Override
    public void deleteShopCan(int id) {
        shopCanRepository.deleteById(id);
        LOGGER.info("Shop can with id - {} , was deleted",id);
    }
}