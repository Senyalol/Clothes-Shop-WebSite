package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductPriceCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductPriceCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p == null || p.getPrice() == null || p.getPrice() <= 0){
            result = false;
            LOGGER.warn("Error adding product, price is null or empty - {} ",p.getPrice());
        }

        return result;
    }

}
