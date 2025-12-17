package com.ClotheShop.CShop.Service.Product.Checks.Filters;

import com.ClotheShop.CShop.Entity.Product;

public class FilterSizeProd implements Filter{

    @Override
    public Object getFilter(Product product) {

        if(product != null && product.getSize() != null){
            return product.getSize();
        }

        return null;
    }

}