package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ClotheShop.CShop.Entity.Product;

public class ProductPriceUpdateCheck implements ProductUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ProductPriceUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getPrice() != null && newData.getPrice() > 0 && newData.getPrice() != oldData.getPrice()){
            Double oldPrice = oldData.getPrice();
            oldData.setPrice(newData.getPrice());
            LOGGER.info("{} Product {} price was updated from {} to {}",oldData.getId(),oldData.getName(),oldPrice,newData.getPrice());

        }

    }
}
