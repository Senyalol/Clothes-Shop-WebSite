package com.ClotheShop.CShop.Service.ProductImage.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.ProductImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductImageImageCreateCheck implements ProductImageCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductImageImageCreateCheck.class);

    @Override
    public boolean check(ProductImage productImage) {

        boolean result = true;

        if(productImage == null || productImage.getImage() == null || productImage.getImage().isEmpty()){

            result = false;
            LOGGER.warn("Error creating product photo");
        }

        return result;
    }

}