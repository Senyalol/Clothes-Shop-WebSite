package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductTypeCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductTypeCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p == null || p.getType() == null || p.getType().isEmpty()){
            result = false;
            LOGGER.warn("Error adding product, type is null or empty");
        }

        return result;
    }

}