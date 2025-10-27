package com.ClotheShop.CShop.Service.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

public class ProductColorUpdateCheck implements ProductUpdateCheck{
    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData.getColor() != null && !newData.getColor().equals(oldData.getColor())) {
            oldData.setColor(newData.getColor());
        }

    }
}
