package com.ClotheShop.CShop.Service.ProductImage.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.ProductImage;

import java.util.List;

public class MainProductImageCreateCheck {

    private List<ProductImageCreateCheck> createChecks;

    public MainProductImageCreateCheck(List<ProductImageCreateCheck> checks) {
        this.createChecks = checks;
    }

    public boolean createCheck(ProductImage productImage){

        boolean result = true;

        for(ProductImageCreateCheck check : createChecks){

            if(!check.check(productImage)){
                result = false;
            }

        }

        return result;
    }

}