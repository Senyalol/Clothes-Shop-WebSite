package com.ClotheShop.CShop.Service.Favorite;

import com.ClotheShop.CShop.Entity.Favorite;
import com.ClotheShop.CShop.Repository.FavoriteRepository;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Repository.UserRepository;
import com.ClotheShop.CShop.Security.JWTService;
import com.ClotheShop.CShop.Service.Favorite.Checks.CreateChecks.FavoriteCreateCheck;
import com.ClotheShop.CShop.Service.Favorite.Checks.CreateChecks.FavoriteProductIdCreateCheck;
import com.ClotheShop.CShop.Service.Favorite.Checks.CreateChecks.MainFavoriteCreateCheck;
import com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks.FavoriteProductIdUpdateCheck;
import com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks.FavoriteUpdateCheck;
import com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks.FavoriteUserIdUpdateCheck;
import com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks.MainFavoriteUpdateCheck;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@JsonSerialize
public class FavoriteServiceImpl implements FavoriteService {

    private static Logger LOGGER = LogManager.getLogger(FavoriteServiceImpl.class);
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final JWTService jwtService;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,UserRepository UserRepository,ProductRepository productRepository, JWTService jwtService) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = UserRepository;
        this.productRepository = productRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    @Override
    public Favorite addFavorite(Favorite favorite) {

        List<FavoriteCreateCheck> createChecks = new ArrayList<>(Arrays.asList(
                new FavoriteProductIdCreateCheck(productRepository)
        ));

        MainFavoriteCreateCheck createCheck = new MainFavoriteCreateCheck(createChecks);

        try{

            if(createCheck.createCheck(favorite)){

                favoriteRepository.save(favorite);
                LOGGER.info("{} - Favorite with userId - {} and productId - successfully added",favorite.getId(),favorite.getUser().getId(),favorite.getProduct().getId());
                return favorite;
            }

        }
        catch(Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite getFavoriteById(int id) {
        return favoriteRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Favorite updateFavorite(int id,Favorite favorite) {

        Favorite oldFav = favoriteRepository.findById(id).get();

        List<FavoriteUpdateCheck> checks = new ArrayList<>(Arrays.asList(
           new FavoriteUserIdUpdateCheck(userRepository),
           new FavoriteProductIdUpdateCheck(productRepository)
        ));

        MainFavoriteUpdateCheck updateCheck = new MainFavoriteUpdateCheck(checks);

        try{

            updateCheck.updateChecks(oldFav,favorite);
            LOGGER.info("{} - Favorite data was updated",id);
            return favoriteRepository.findById(id).get();
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    @Transactional
    @Override
    public void deleteFavoriteById(int id) {
        favoriteRepository.deleteById(id);
        LOGGER.info("Favorite with id - {} , was deleted",id);
    }

    @Override
    public List<Favorite> getAllFavoritesByUser(String token) {

        String payLoadData = token;

        if(payLoadData != null && payLoadData.startsWith("Bearer ")) {
            payLoadData = payLoadData.substring(7).trim();
        }

        int yourUserId = userRepository.findByLogin(jwtService.getLoginFromToken(payLoadData)).get().getId();

        return favoriteRepository.findAllByUserId(yourUserId);
    }

    @Transactional
    @Override
    public void deleteFromFavorite(int id, Favorite favorite) {


        int yourUserId = favorite.getUser().getId();

        if(favoriteRepository.findById(id).get().getUser().getId() == yourUserId){
            favoriteRepository.deleteById(id);
            LOGGER.info("Favorite with id - {} , was deleted",id);
        }


    }
}