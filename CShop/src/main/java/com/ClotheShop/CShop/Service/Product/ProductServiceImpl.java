package com.ClotheShop.CShop.Service.Product;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Service.Product.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@JsonSerialize
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {

        List<ProductCreateCheck> createChecks = new ArrayList<>(Arrays.asList(
                new ProductNameCreateCheck(),
                new ProductPriceCreateCheck(),
                new ProductAmountCreateCheck(),
                new ProductAvailabilityCreateCheck(),
                new ProductColorCreateCheck(),
                new ProductSizeCreateCheck(),
                new ProductSexCreateCheck(),
                new ProductCategoryCreateCheck(),
                new ProductTypeCreateCheck()
        ));
        MainProductCreateCheck checks = new MainProductCreateCheck(createChecks);

        if(checks.AllChecks(product)){

        productRepository.save(product);
            return product;
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getCertainProduct(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Product updateProduct(Product product, int id) {

        List<ProductUpdateCheck> updateChecks = new ArrayList<>(
                Arrays.asList(
                    new ProductNameUpdateCheck(),
                    new ProductPriceUpdateCheck(),
                    new ProductAmountUpdateCheck(),
                    new ProductAvailabilityUpdateCheck(),
                    new ProductColorUpdateCheck(),
                    new ProductSizeUpdateCheck(),
                    new ProductSexUpdateCheck(),
                    new ProductCategoryUpdateCheck(),
                    new ProductTypeUpdateCheck()
                )
        );

        MainProductUpdateCheck checks = new MainProductUpdateCheck(updateChecks);

        Product oldProduct = productRepository.findById(id).get();
        checks.allChecks(product, oldProduct);

        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

}
