package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductColorCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductColorCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p == null || p.getColor() == null || p.getColor().isEmpty()){
            result = false;
            LOGGER.warn("Error adding product , color is null or empty");
        }
        return result;
    }

}