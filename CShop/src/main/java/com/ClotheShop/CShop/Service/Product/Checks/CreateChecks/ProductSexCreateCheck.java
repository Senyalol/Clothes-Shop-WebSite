package com.ClotheShop.CShop.Service.Product.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Service.Product.Checks.ProductSex;

public class ProductSexCreateCheck implements ProductCreateCheck {

    @Override
    public boolean check(Product p) {

        boolean result = true;

        //!p.getSex().equals(ProductSex.мужской.toString()) || !p.getSex().equals(ProductSex.женский.toString())
        if(p.getSex() == null ){
            result = false;
        }
        else if(!p.getSex().equals(ProductSex.мужской.name())){

            if(!p.getSex().equals(ProductSex.женский.name())){
                result = false;
            }

        }

        return result;

    }

}