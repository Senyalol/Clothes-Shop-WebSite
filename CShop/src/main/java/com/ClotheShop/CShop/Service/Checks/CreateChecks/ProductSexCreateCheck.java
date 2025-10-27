package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductSexCreateCheck implements ProductCreateCheck {

    @Override
    public boolean check(Product p) {

        boolean result = true;

        //!p.getSex().equals(ProductSex.мужской.toString()) || !p.getSex().equals(ProductSex.женский.toString())
        if(p.getSex() == null ){
            result = false;
        }

        return result;

    }

}