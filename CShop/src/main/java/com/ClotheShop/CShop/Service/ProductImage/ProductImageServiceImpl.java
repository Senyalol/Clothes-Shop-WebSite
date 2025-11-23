package com.ClotheShop.CShop.Service.ProductImage;

import com.ClotheShop.CShop.Entity.ProductImage;
import com.ClotheShop.CShop.Repository.ProductImageRepository;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Service.ProductImage.Checks.CreateChecks.MainProductImageCreateCheck;
import com.ClotheShop.CShop.Service.ProductImage.Checks.CreateChecks.ProductImageCreateCheck;
import com.ClotheShop.CShop.Service.ProductImage.Checks.CreateChecks.ProductImageImageCreateCheck;
import com.ClotheShop.CShop.Service.ProductImage.Checks.CreateChecks.ProductImageProductIdCreateCheck;
import com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks.MainProductImageUpdateCheck;
import com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks.ProductImageImageUpdateCheck;
import com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks.ProductImageProductIdUpdateCheck;
import com.ClotheShop.CShop.Service.ProductImage.Checks.UpdateChecks.ProductImageUpdateCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private static Logger LOGGER = LogManager.getLogger(ProductImageServiceImpl.class);
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductImageServiceImpl(ProductImageRepository productImageRepository,ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductImage addProductImage(ProductImage productImage) {

        List<ProductImageCreateCheck> cChecks = new ArrayList<>(Arrays.asList(
                new ProductImageProductIdCreateCheck(productRepository),
                new ProductImageImageCreateCheck()
        ));

        MainProductImageCreateCheck createChecks = new MainProductImageCreateCheck(cChecks);

        try{

            if(createChecks.createCheck(productImage)){

                productImageRepository.save(productImage);
                LOGGER.info("Product image added");
                return productImage;
            }

        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getCause().toString());
        }

        return null;
    }

    @Override
    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage getProductImageById(int id) {
        return productImageRepository.findById(id).get();
    }

    @Transactional
    @Override
    public ProductImage updateProductImage(int id, ProductImage productImage) {

        ProductImage oldImage = productImageRepository.findById(id).get();

        List<ProductImageUpdateCheck> checks = new ArrayList<>(Arrays.asList(
           new ProductImageProductIdUpdateCheck(productRepository),
                new ProductImageImageUpdateCheck()
        ));

        MainProductImageUpdateCheck updateCheck = new MainProductImageUpdateCheck(checks);

        try{

            updateCheck.updateChecks(oldImage,productImage);
            LOGGER.info("Product image with id - {} ,was updated",id);
            return productImageRepository.findById(id).get();
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getCause().toString());
        }

    }

    @Transactional
    @Override
    public void deleteById(int id) {
        productImageRepository.deleteById(id);
    }
}
