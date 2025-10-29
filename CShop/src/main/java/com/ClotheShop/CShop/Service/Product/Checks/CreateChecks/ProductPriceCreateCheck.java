package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductPriceCreateCheck implements ProductCreateCheck {

    @Override
    public boolean check(Product p) {

        boolean result = true;

        if(p.getPrice() == null || p.getPrice() <= 0){
            result = false;
        }

        return result;
    }

}
