package com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.Product;

import java.util.List;

public class MainProductUpdateCheck {

    private List<ProductUpdateCheck> checks;

    public MainProductUpdateCheck(List<ProductUpdateCheck> checks) {
        this.checks = checks;
    }

    public void allChecks(Product newProduct,Product oldProduct) {

        for (ProductUpdateCheck check : checks) {

            check.updateCheck(newProduct, oldProduct);

        }

    }

}
