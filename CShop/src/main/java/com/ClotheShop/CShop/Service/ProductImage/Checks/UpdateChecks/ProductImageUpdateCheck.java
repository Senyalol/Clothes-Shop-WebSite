package com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ProductImage;

public interface ProductImageUpdateCheck {

    void check(ProductImage oldImage, ProductImage newImage);

}