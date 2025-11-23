package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductAmountCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductAmountCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p.getAmount() == null || p.getAmount() < 0){
            result = false;
            LOGGER.warn("Error adding product , incorrect amount - {}",p.getAmount());
        }

        return result;
    }

}