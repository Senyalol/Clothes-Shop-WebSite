package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductSizeUpdateCheck implements ProductUpdateCheck{
    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getSize() != null && newData.getSize() >= 0){

            oldData.setSize(newData.getSize());

        }

    }
}