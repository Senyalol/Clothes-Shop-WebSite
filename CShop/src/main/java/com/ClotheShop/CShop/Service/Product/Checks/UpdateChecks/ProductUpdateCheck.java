package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public interface ProductUpdateCheck {

    void updateCheck(Product newData, Product oldData);

}
