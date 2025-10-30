package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductSizeUpdateCheck implements ProductUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(ProductSizeUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getSize() != null && newData.getSize() >= 0){
            Integer oldSize = oldData.getSize();
            oldData.setSize(newData.getSize());
            LOGGER.info("{} Product {} size was updated from {} to {}", oldData.getId() ,oldData.getName() ,oldSize, newData.getSize());

        }

    }
}