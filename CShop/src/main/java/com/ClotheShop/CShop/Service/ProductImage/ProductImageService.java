package com.ClotheShop.CShop.Service.ProductImage;

import com.ClotheShop.CShop.Entity.ProductImage;

import java.util.List;

public interface ProductImageService {

    List<ProductImage> getAllProductImages();

    ProductImage getProductImageById(int id);

    ProductImage addProductImage(ProductImage productImage);

    ProductImage updateProductImage(int id,ProductImage productImage);

    void deleteById(int id);

}