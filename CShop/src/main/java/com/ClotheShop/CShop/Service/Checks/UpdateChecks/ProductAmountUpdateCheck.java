package com.ClotheShop.CShop.Service.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductAmountUpdateCheck implements ProductUpdateCheck{

    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getAmount() != null
        && newData.getAmount() >= 0 && newData.getAmount() != oldData.getAmount()) {

            oldData.setAmount(newData.getAmount());

        }

    }

}
