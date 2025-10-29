package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductPriceUpdateCheck implements ProductUpdateCheck{

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getPrice() != null && newData.getPrice() > 0 && newData.getPrice() != oldData.getPrice()){

            oldData.setPrice(newData.getPrice());

        }

    }
}
