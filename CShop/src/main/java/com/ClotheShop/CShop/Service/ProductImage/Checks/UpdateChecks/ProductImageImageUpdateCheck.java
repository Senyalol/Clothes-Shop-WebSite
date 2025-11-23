package com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks;

import com.ClotheShop.CShop.Entity.ProductImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductImageImageUpdateCheck implements ProductImageUpdateCheck{

    private static Logger LOGGER = LogManager.getLogger(ProductImageImageUpdateCheck.class);

    @Override
    public void check(ProductImage oldImage, ProductImage newImage) {

        if(newImage != null && newImage.getImage() != null && !newImage.getImage().isEmpty()){

            String oldImageStr = oldImage.getImage();
            oldImage.setImage(newImage.getImage());
            LOGGER.info("{} - Product image was updated from - {} to - {}",oldImage.getId(),oldImageStr,oldImage.getImage());

        }

    }

}