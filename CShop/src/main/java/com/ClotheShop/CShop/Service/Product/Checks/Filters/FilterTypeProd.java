package com.ClotheShop.CShop.Service.Product.Checks.Filters;

import com.ClotheShop.CShop.Entity.Product;

public class FilterTypeProd implements Filter {

    @Override
    public Object getFilter(Product product) {

        if(product != null && product.getType() != null){
            return product.getType();
        }

        return null;
    }

}