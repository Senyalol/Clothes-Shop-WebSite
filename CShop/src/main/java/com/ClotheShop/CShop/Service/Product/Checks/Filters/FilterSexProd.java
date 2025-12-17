package com.ClotheShop.CShop.Service.Product.Checks.Filters;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Service.Product.Checks.ProductSex;

public class FilterSexProd implements Filter {

    @Override
    public Object getFilter(Product product) {

        if(product != null && product.getSex() != null){

            if(product.getSex().equals(ProductSex.мужской.name()) || product.getSex().equals(ProductSex.женский.name())){
                return product.getSex();
            }

        }
        return null;
    }

}