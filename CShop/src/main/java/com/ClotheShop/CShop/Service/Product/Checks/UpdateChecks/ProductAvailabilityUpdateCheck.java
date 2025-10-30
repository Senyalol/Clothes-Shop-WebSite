package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductAvailabilityUpdateCheck implements ProductUpdateCheck{
    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData.getAvailability() != null){

            oldData.setAvailability(newData.getAvailability());

        }

    }
}