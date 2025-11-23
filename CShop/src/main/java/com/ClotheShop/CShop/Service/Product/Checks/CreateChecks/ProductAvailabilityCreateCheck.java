package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductAvailabilityCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductAvailabilityCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;
        if(p == null || p.getAvailability() == null){
            result = false;
            LOGGER.warn("Error adding product, product availability is null");
        }
        return result;
    }

}
