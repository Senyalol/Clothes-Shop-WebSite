package com.ClotheShop.CShop.Service.Product.Checks.Filters;

import com.ClotheShop.CShop.Entity.Product;

public class FilterCategoryProd implements Filter {

    @Override
    public Object getFilter(Product product) {

        if(product != null && product.getCategory() != null){
            return product.getCategory();
        }
        return null;
    }

}