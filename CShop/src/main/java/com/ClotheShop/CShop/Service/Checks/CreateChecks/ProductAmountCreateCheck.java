package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductAmountCreateCheck implements ProductCreateCheck {

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p.getAmount() == null || p.getAmount() < 0){
            result = false;
        }

        return result;
    }

}