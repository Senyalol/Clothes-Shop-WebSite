package com.ClotheShop.CShop.Service.Product;

import com.ClotheShop.CShop.Entity.Product;
import com.ClotheShop.CShop.Repository.ProductRepository;
import com.ClotheShop.CShop.Service.Product.Checks.CreateChecks.*;
import com.ClotheShop.CShop.Service.Product.Checks.Filters.*;
import com.ClotheShop.CShop.Service.Product.Checks.UpdateChecks.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

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
        LOGGER.info("Product: {} - successfully saved", product.getName());
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
        LOGGER.info("Product data : - was successfully updated");

        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        LOGGER.info("Product: {} - was successfully deleted", productRepository.findById(id).get().getName());
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Product> filterProduct(Product product) {

        try {

            List<Filter> filters = new ArrayList<>(Arrays.asList(
                    new FilterNameProd(),
                    new FilterPriceProd(),
                    new FilterSizeProd(),
                    new FilterSexProd(),
                    new FilterCategoryProd(),
                    new FilterTypeProd()
            ));

            MainFilter result = new MainFilter(filters);

            return result.getFilters(product,productRepository);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

}