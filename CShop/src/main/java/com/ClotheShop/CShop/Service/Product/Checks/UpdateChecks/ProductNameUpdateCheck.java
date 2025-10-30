package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductNameUpdateCheck implements ProductUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(ProductNameUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getName() != null && !newData.getName().equals(oldData.getName())){
            String oldName = oldData.getName();
            oldData.setName(newData.getName());
            LOGGER.info("Product name was updated from {} to {}", oldName, newData.getName());
        }

    }

}
