package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductCategoryUpdateCheck implements ProductUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(ProductCategoryUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getCategory() != null){

            String oldCategory = oldData.getCategory();
            oldData.setCategory(newData.getCategory());
            LOGGER.info("{} Product {} category was updated from {} to {}",oldData.getId(),oldData.getName() ,oldCategory, newData.getCategory());

        }

    }
}