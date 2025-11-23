package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductNameCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductNameCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;
        if(p == null || p.getName() == null || p.getName().isEmpty()){
            result = false;
            LOGGER.warn("Error adding product, name is null or empty");
        }

        return result;
    }

}
