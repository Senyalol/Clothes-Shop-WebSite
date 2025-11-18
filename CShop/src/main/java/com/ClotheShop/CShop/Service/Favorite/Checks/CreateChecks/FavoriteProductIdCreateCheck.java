package com.ClotheShop.CShop.Service.Favorite.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Favorite;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FavoriteProductIdCreateCheck implements FavoriteCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(FavoriteProductIdCreateCheck.class);
    private ProductRepository productRepository;

    public FavoriteProductIdCreateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean check(Favorite favorite) {

        boolean result = true;

        if(favorite == null || favorite.getProduct() == null
                || productRepository.findById(favorite.getProduct().getId()).isEmpty()){

            result = false;
            LOGGER.warn("Error adding to favorites - product - {} , not found",favorite.getProduct().getId());
        }

        return result;
    }

}