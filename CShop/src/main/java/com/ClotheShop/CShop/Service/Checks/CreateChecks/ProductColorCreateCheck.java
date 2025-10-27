package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductColorCreateCheck implements ProductCreateCheck {
    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p.getColor() == null || p.getColor().isEmpty()){
            result = false;
        }
        return result;
    }
}
