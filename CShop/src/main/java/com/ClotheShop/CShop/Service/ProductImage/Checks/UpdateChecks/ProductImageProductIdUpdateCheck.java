package com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ProductImage;
import com.ClotheShop.CShop.Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductImageProductIdUpdateCheck implements ProductImageUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ProductImageProductIdUpdateCheck.class);
    private ProductRepository productRepository;

    public ProductImageProductIdUpdateCheck(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void check(ProductImage oldImage, ProductImage newImage) {

        if(newImage != null && newImage.getProduct() != null && productRepository.findById(newImage.getProduct().getId()).isPresent()){

            Integer oldProductId = oldImage.getProduct().getId();
            oldImage.setProduct(newImage.getProduct());
            LOGGER.info("{} - Product image was updated from - {} to - {}",oldImage.getId(),oldProductId,oldImage.getProduct().getId());

        }

    }

}