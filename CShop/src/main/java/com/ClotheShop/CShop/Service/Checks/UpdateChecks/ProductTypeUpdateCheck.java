package com.ClotheShop.CShop.Service.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductTypeUpdateCheck implements ProductUpdateCheck{
    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getType() != null){

            oldData.setType(newData.getType());

        }

    }
}
