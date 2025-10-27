package com.ClotheShop.CShop.Service.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Service.Checks.ProductSex;

public class ProductSexUpdateCheck implements ProductUpdateCheck{
    @Override
    public void updateCheck(Product newData, Product oldData) {

        if(newData != null && newData.getSex() != null){

            if(newData.getSex().equals(ProductSex.мужской) || newData.getSex().equals(ProductSex.женский)){

                oldData.setSex(newData.getSex());

            }

        }

    }
}
