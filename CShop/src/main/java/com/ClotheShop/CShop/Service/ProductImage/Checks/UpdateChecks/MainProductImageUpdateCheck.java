package com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ProductImage;

import java.util.List;

public class MainProductImageUpdateCheck {

    private List<ProductImageUpdateCheck> checks;

    public MainProductImageUpdateCheck(List<ProductImageUpdateCheck> checks) {
        this.checks = checks;
    }

    public void updateChecks(ProductImage oldImage, ProductImage newImage) {

        for(ProductImageUpdateCheck check : checks) {

            check.check(oldImage, newImage);

        }

    }

}