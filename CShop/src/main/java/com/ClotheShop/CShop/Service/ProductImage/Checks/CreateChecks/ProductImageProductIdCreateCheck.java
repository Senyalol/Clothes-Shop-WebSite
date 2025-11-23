package com.ClotheShop.CShop.Service.ProductImage.Checks.CreateChecks;

import com.ClotheShop.CShop.Entity.ProductImage;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductImageProductIdCreateCheck implements ProductImageCreateCheck {

    private static Logger LOGGER = LogManager.getLogger(ProductImageProductIdCreateCheck.class);
    private ProductRepository productRepository;

    public ProductImageProductIdCreateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean check(ProductImage productImage) {

        boolean result = true;

        if(productImage == null || productImage.getProduct() == null || productRepository.findById(productImage.getProduct().getId()).isEmpty()){
            result = false;
            LOGGER.warn("Error creating product photo, because product with id - {} , not found",productImage.getProduct().getId());
        }

        return result;
    }

}