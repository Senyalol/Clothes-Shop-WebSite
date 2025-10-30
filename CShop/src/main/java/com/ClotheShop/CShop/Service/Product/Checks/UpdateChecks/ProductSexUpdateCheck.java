package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Service.Product.Checks.ProductSex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductSexUpdateCheck implements ProductUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(ProductSexUpdateCheck.class);

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getSex() != null){

            if(newData.getSex().equals(ProductSex.мужской.name()) || newData.getSex().equals(ProductSex.женский.name())){

                String oldSex = oldData.getSex();
                oldData.setSex(newData.getSex());
                LOGGER.info("{} Product {} sex was updated from {} to {}",oldData.getId(),oldData.getName(),oldSex, newData.getSex());

            }

        }

    }
}
