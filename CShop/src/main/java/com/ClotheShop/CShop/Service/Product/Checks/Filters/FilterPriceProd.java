package com.ClotheShop.CShop.Service.Product.Checks.Filters;

import com.ClotheShop.CShop.Entity.Product;

public class FilterPriceProd implements Filter {

    @Override
    public Object getFilter(Product product) {

        if(product != null && product.getPrice() != null){
            return product.getPrice();
        }

        return null;
    }

}