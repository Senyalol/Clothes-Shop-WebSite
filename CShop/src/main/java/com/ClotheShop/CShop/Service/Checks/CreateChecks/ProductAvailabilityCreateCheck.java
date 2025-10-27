package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductAvailabilityCreateCheck implements ProductCreateCheck {

    @Override
    public boolean check(Product p) {

        boolean result = true;
        if(p.getAvailability() == null){
            result = false;
        }
        return result;
    }

}
