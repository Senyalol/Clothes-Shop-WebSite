package com.ClotheShop.CShop.Service.ShopCan;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Entity.ShopCan;
import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.ShopCanRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
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

    private final JWTService jwtService;

    @Autowired
    public ShopCanServiceImpl(ShopCanRepository shopCanRepository,UserRepository userRepository,ProductRepository productRepository,JWTService jwtService) {
        this.shopCanRepository = shopCanRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.jwtService = jwtService;
    }

    @Override
    public List<ShopCan> getAllShopCan() {
        return shopCanRepository.findAll();
    }

    @Override
    public List<ShopCan> getAllProductsByUserId(int userId) {
        return shopCanRepository.findByUserId(userId);
    }

    @Override
    public ShopCan getShopCanById(int id) {
        return shopCanRepository.findById(id).get();
    }

    @Transactional
    @Override
    public ShopCan addToShopCan(ShopCan shopCan) {

        List<ShopCanCreateCheck> checks = new ArrayList<>(Arrays.asList(
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

    @Override
    public List<ShopCan> getMyShopCan(String token) {

        int yourUserId = userRepository.findByLogin(jwtService.getLoginFromToken(token)).get().getId();

        return shopCanRepository.findByUserId(yourUserId);

    }

    @Transactional
    @Override
    public void deleteFromMyShopCan(int id, String token) {

        int yourUserId = userRepository.findByLogin(jwtService.getLoginFromToken(token)).get().getId();

        if(yourUserId == shopCanRepository.findById(id).get().getUser().getId()){
            shopCanRepository.deleteById(id);
            LOGGER.info("Shop can with id - {} , was deleted",id);
        }

    }

    @Transactional
    @Override
    public List<ShopCan> paid(String token) {

        int yourUserId = userRepository.findByLogin(jwtService.getLoginFromToken(token)).get().getId();

        Double yourBalance = userRepository.findById(yourUserId).get().getBalance();

        Double totalCost = getTotalCost(yourUserId);

        if(yourBalance >= totalCost){

            User your = userRepository.findById(yourUserId).get();

            yourBalance = yourBalance - totalCost;

            your.setBalance(yourBalance);

            sendProduct(yourUserId);

            cleanShopCan(yourUserId);

        }
        else{
            LOGGER.info("Недостаточно средств на балансе у пользователя - {}, баланс - {}, стоимость покупки - {}",yourUserId,yourBalance,totalCost);
        }

        return shopCanRepository.findByUserId(yourUserId);
    }

    private void cleanShopCan(int userId) {

        List<ShopCan> shopCan = shopCanRepository.findByUserId(userId);

        for(ShopCan shopCanItem : shopCan){

            int id = shopCanItem.getId();
            shopCanRepository.deleteById(id);

        }

    }

    private void sendProduct(int userId){

        List<ShopCan> products = shopCanRepository.findByUserId(userId);

        for(ShopCan product : products){

            Product productFromCan = product.getProduct();

            Integer amount = productFromCan.getAmount();
            amount -= 1;

            productFromCan.setAmount(amount);

        }

    }

    private Double getTotalCost(int userId){

        Double totalCost = 0.0;
        List<ShopCan> products = shopCanRepository.findByUserId(userId);

        for(ShopCan shopCan : products){
            totalCost += shopCan.getProduct().getPrice();
        }

        return totalCost;
    }

}