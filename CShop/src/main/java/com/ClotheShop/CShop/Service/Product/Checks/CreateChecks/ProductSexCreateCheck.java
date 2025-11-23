package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Service.Product.Checks.ProductSex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductSexCreateCheck implements ProductCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductSexCreateCheck.class);

    @Override
    public boolean check(Product p) {

        boolean result = true;

        //!p.getSex().equals(ProductSex.мужской.toString()) || !p.getSex().equals(ProductSex.женский.toString())
        if(p == null || p.getSex() == null || p.getSex().isEmpty()){
            result = false;
            LOGGER.warn("Error adding product, sex is null or empty");
        }
        else if(!p.getSex().equals(ProductSex.мужской.name())){

            if(!p.getSex().equals(ProductSex.женский.name())){
                result = false;
            }

        }

        return result;

    }

}