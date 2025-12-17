package com.ClotheShop.CShop.Service.Product.Checks.Filters;

import com.ClotheShop.CShop.Entity.Product;

public class FilterNameProd implements Filter {

    @Override
    public Object getFilter(Product product) {

        if(product != null && product.getName() != null){
            return product.getName();
        }

        return null;
    }

}