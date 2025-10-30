package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductAmountUpdateCheck implements ProductUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(ProductAmountUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getAmount() != null
        && newData.getAmount() >= 0 && newData.getAmount() != oldData.getAmount()) {

            Integer oldAmount = oldData.getAmount();
            oldData.setAmount(newData.getAmount());
            LOGGER.info("{} Product {} amount was updated from {} to {}", oldData.getId(),oldData.getName(),oldAmount, newData.getAmount());

        }

    }

}
