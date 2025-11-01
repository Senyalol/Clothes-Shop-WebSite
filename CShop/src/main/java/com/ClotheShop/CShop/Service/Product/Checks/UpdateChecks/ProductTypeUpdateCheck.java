package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductTypeUpdateCheck implements ProductUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(ProductTypeUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getType() != null){

            String oldType = oldData.getType();
            oldData.setType(newData.getType());
            LOGGER.info("{} Product {} type was update from {} to {}", oldData.getId(),oldData.getName(),oldType,newData.getType());

        }

    }
}