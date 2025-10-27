package com.ClotheShop.CShop.Service.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductNameUpdateCheck implements ProductUpdateCheck{

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getName() != null && !newData.getName().equals(oldData.getName())){

            oldData.setName(newData.getName());

        }

    }

}
