package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductCategoryUpdateCheck implements ProductUpdateCheck{
    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getCategory() != null){

            oldData.setCategory(newData.getCategory());

        }

    }
}