package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductSizeCreateCheck implements ProductCreateCheck {
    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p.getSize() == null || p.getSize() <= 0){
            result = false;
        }
        return result;
    }
}
