package com.ClotheShop.CShop.Service.ShopCan.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCanProductIdUpdateCheck implements ShopCanUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ShopCanProductIdUpdateCheck.class);
    private ProductRepository productRepository;

    public ShopCanProductIdUpdateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void check(ShopCan oldShop, ShopCan newShop) {

        if(newShop != null && newShop.getProduct().getId() != null
                && productRepository.findById(newShop.getProduct().getId()).isPresent()){

            Integer oldProductId = oldShop.getProduct().getId();
            oldShop.setProduct(newShop.getProduct());
            LOGGER.info("{} - Shop Can product was updated from - {} , to - {}",oldShop.getId(),oldProductId,oldShop.getProduct().getId());

        }

    }

}