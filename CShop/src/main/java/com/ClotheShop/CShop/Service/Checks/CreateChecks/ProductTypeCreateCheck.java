package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductTypeCreateCheck implements ProductCreateCheck {
    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p.getType() == null || p.getType().isEmpty()){
            result = false;
        }
        return result;
    }
}
