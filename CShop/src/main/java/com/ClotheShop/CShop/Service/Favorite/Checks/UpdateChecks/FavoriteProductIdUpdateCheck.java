package com.ClotheShop.CShop.Service.Favorite.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Favorite;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FavoriteProductIdUpdateCheck implements FavoriteUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(FavoriteProductIdUpdateCheck.class);
    private ProductRepository productRepository;

    public FavoriteProductIdUpdateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void check(Favorite oldFav, Favorite newFav) {

        if(newFav != null && newFav.getProduct() != null && productRepository.findById(newFav.getProduct().getId()).isPresent()){

            oldFav.setProduct(newFav.getProduct());
            Integer oldProductId = oldFav.getProduct().getId();
            LOGGER.info("{} - Favorite product was updated from - {} to - {}",oldFav.getId(),oldProductId,oldFav.getProduct().getId());
        }
        else{
            oldFav.setProduct(oldFav.getProduct());
        }

    }

}