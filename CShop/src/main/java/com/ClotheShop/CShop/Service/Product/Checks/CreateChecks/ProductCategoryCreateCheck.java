package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductCategoryCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductCategoryCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p == null || p.getCategory() == null || p.getCategory().isEmpty()){
            result = false;
            LOGGER.error("Error adding product, category is null or empty");
        }
        return result;
    }
}
