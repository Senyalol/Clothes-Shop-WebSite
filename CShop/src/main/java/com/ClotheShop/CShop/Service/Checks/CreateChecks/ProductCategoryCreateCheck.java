package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductCategoryCreateCheck implements ProductCreateCheck {

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p.getCategory() == null || p.getCategory().isEmpty()){
            result = false;
        }
        return result;
    }
}
