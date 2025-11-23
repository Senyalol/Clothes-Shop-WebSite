package com.ClotheShop.CShop.Service.ShopCan.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.ShopCan;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCanProductIdCreateCheck implements ShopCanCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ShopCanProductIdCreateCheck.class);
    private ProductRepository productRepository;

    public ShopCanProductIdCreateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean check(ShopCan shopCan) {

        boolean result = true;

        if(shopCan == null || shopCan.getProduct() == null
                || productRepository.findById(shopCan.getProduct().getId()).isEmpty()){

            result = false;
            LOGGER.warn("Error adding to cart , product with id - {} , not found",shopCan.getProduct().getId());

        }

        return result;
    }

}