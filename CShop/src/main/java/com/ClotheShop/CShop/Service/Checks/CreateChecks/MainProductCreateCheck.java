package com.ClotheShop.CShop.Service.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.Product;

import java.util.List;

public class MainProductCreateCheck {

    private List<ProductCreateCheck> checks;

    public MainProductCreateCheck(List<ProductCreateCheck> checks) {
        this.checks = checks;
    }

    public boolean AllChecks(Product product) {

        boolean result = true;

        for (ProductCreateCheck check : checks) {

            if(!check.check(product)) {
                result = false;
            }

        }
        return result;
    }

}
