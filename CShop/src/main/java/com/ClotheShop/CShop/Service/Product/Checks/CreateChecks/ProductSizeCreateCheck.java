package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductSizeCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductSizeCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p == null || p.getSize() == null || p.getSize() <= 0){
            result = false;
            LOGGER.warn("Error adding product, size is null or empty - {}",p.getSize());
        }
        return result;
    }

}
