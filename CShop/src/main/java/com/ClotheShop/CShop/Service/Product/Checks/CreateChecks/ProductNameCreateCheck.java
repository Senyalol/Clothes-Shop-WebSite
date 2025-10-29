package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductNameCreateCheck implements ProductCreateCheck {

    @Override
    public boolean check(Product p) {

        boolean result = true;
        if(p.getName() == null || p.getName().isEmpty()){
            result = false;
        }

        return result;
    }

}
