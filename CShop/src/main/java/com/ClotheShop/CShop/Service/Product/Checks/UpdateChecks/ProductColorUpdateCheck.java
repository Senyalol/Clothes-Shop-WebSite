package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductColorUpdateCheck implements ProductUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(ProductColorUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData.getColor() != null && !newData.getColor().equals(oldData.getColor())) {
            String oldColor = oldData.getColor();
            oldData.setColor(newData.getColor());
            LOGGER.info("{} Product {} color was updated from {} to {}",oldData.getId(),oldData.getName() ,oldColor, newData.getColor());
        }

    }
}
